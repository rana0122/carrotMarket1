package miniproject.carrotmarket1.controller;

import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.entity.ChatRoom;
import miniproject.carrotmarket1.entity.User;
import miniproject.carrotmarket1.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatController(ChatRoomService chatRoomService) {

        this.chatRoomService = chatRoomService;
    }

    // 채팅룸 목록 보기
    @GetMapping("/chatroom")
    public String listChatRooms(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        List<ChatRoom> chatRooms = chatRoomService.findAllByUser(loggedInUser.getId());
        model.addAttribute("chatRooms", chatRooms);
        return "chat/chatroom";  // chatroom.html로 이동
    }

    // 특정 상품에 대한 채팅룸 생성
    @PostMapping("/chatroom/create")
    public String createChatRoom(@RequestParam Long productId,
                                 @RequestParam Long sellerId,
                                 HttpSession session,
                                 Model model) {
        // 로그인한 사용자 확인
        User buyer = (User) session.getAttribute("loggedInUser");
        if (buyer == null) {
            model.addAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        // 채팅방 생성
        ChatRoom chatRoom = chatRoomService.createChatRoom(productId, sellerId, buyer.getId());
        // 생성된 채팅방으로 리다이렉트
        return "redirect:/chatroom/" + chatRoom.getId().toString();
    }

    //채팅룸 입장
    @GetMapping("/chatroom/{id}")
    public String enterChatRoom(@PathVariable Long id, HttpSession session, Model model) {
        Optional<ChatRoom> chatRoomOptional = chatRoomService.findById(id);
        if (chatRoomOptional.isPresent()) {
            ChatRoom chatRoom = chatRoomOptional.get();
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return "redirect:/login";  // 로그인 정보가 없으면 로그인 페이지로 리디렉션
            }
            model.addAttribute("chatRoom", chatRoom);
            model.addAttribute("loggedInUser", loggedInUser);
            return "chat/chat";  // chat.html로 이동하여 채팅 진행
        } else {
            return "redirect:/error";  // 채팅방을 찾을 수 없을 때
        }
    }
}