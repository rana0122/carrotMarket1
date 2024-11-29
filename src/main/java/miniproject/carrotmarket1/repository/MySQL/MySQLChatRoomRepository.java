package miniproject.carrotmarket1.repository.MySQL;

import miniproject.carrotmarket1.dao.MySQL.ChatRoomDAO;
import miniproject.carrotmarket1.entity.ChatRoom;
import miniproject.carrotmarket1.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySQLChatRoomRepository implements ChatRoomRepository {

    private final ChatRoomDAO chatRoomDAO;

    @Autowired
    public MySQLChatRoomRepository(ChatRoomDAO chatRoomDAO) {

        this.chatRoomDAO = chatRoomDAO;
    }

    //새로운 채팅방 생성
    @Override
    public void insertChatRoom(ChatRoom chatRoom) {
        chatRoomDAO.insertChatRoom(chatRoom);
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        return chatRoomDAO.findById(id);
    }

    @Override
    public List<ChatRoom> findAllByUser(Long id) {
        return chatRoomDAO.findAllByUser(id);
    }

    //채팅룸 찾기
    @Override
    public Optional<ChatRoom> findByProductAndUsers(Long productId, Long buyerId) {
        return chatRoomDAO.findByProductAndUsers(productId, buyerId);
    }


}
