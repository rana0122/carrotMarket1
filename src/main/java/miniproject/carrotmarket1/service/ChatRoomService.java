package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.entity.ChatRoom;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.User;
import miniproject.carrotmarket1.repository.ChatRoomRepository;
import miniproject.carrotmarket1.repository.ProductRepository;
import miniproject.carrotmarket1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }


    public ChatRoom createChatRoom(Long productId, Long buyerId, Long sellerId) {
        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findByProductIdAndBuyerId(productId, buyerId);
        if (existingChatRoom.isPresent()) {
            return existingChatRoom.get();
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setProductId(productId);
        chatRoom.setBuyerId(buyerId);
        chatRoom.setSellerId(sellerId);
        // 저장 후 반환되는 chatRoom에 ID를 설정
        chatRoomRepository.insertChatRoom(chatRoom);

        // chatRoom의 ID가 설정되었는지 확인
        if (chatRoom.getId() == null) {
            // 방금 저장한 채팅룸을 다시 조회하여 ID를 가져옵니다 (예시로 사용하는 코드).
            Optional<ChatRoom> savedChatRoom = chatRoomRepository.findByProductIdAndBuyerId(productId, buyerId);
            if (savedChatRoom.isPresent()) {
                return savedChatRoom.get();
            }
        }
        return chatRoom;
    }

    public Optional<ChatRoom> findById(Long id) {
        return chatRoomRepository.findById(id);
    }

    public List<ChatRoom> findAllByUser(Long id) {
        return chatRoomRepository.findAllByUser(id);
    }
}
