package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.entity.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository {

    void insertChatRoom(ChatRoom chatRoom);
    Optional<ChatRoom> findByProductIdAndBuyerId(Long productId, Long buyerId);
    Optional<ChatRoom> findById(Long id);
    List<ChatRoom> findAllByUser(Long id);
}
