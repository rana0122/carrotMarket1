package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.entity.ChatRoom;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.User;
import miniproject.carrotmarket1.repository.ChatRoomRepository;
import miniproject.carrotmarket1.repository.ProductRepository;
import miniproject.carrotmarket1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Transactional
    public ChatRoom createChatRoom(Long productId, Long sellerId, Long buyerId) {
        // 이미 존재하는 채팅방 확인
        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findByProductAndUsers(productId, buyerId);
        if (existingChatRoom.isPresent()) {
            return existingChatRoom.get();
        }
        // 새 채팅방 생성
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setProductId(productId);
        chatRoom.setBuyerId(buyerId);
        chatRoom.setSellerId(sellerId);

        chatRoomRepository.insertChatRoom(chatRoom);
        return chatRoom;
    }

    public Optional<ChatRoom> findById(Long id) {
        return chatRoomRepository.findById(id);
    }

    public List<ChatRoom> findAllByUser(Long id) {
        return chatRoomRepository.findAllByUser(id);
    }
}
