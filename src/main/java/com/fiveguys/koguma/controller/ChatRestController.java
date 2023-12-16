package com.fiveguys.koguma.controller;

import com.fiveguys.koguma.data.dto.ChatroomDTO;
import com.fiveguys.koguma.data.dto.MemberDTO;
import com.fiveguys.koguma.data.dto.ProductDTO;
import com.fiveguys.koguma.service.chat.ChatService;
import com.fiveguys.koguma.service.member.MemberService;
import com.fiveguys.koguma.service.product.ProductService;
import com.fiveguys.koguma.util.annotation.CurrentMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chat")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChatRestController {
    private final ChatService chatService;
    private final ProductService productService;
    private final MemberService memberService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<ChatroomDTO>> listChatRoom (
            @CurrentMember MemberDTO memberDTO
    ) {
        return ResponseEntity.ok().body(chatService.listChatroom(memberDTO));
    }

    @RequestMapping(value = "/list/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ChatroomDTO>> listChatRoomBySeller(
            @CurrentMember MemberDTO memberDTO,
            @PathVariable Long productId
    ) throws Exception {
        ProductDTO productDTO = productService.getProduct(productId);
        if(!productDTO.getSellerDTO().getId().equals(memberDTO.getId())) {
            throw new Exception("권한이 없습니다.");
        }
        List<ChatroomDTO> chatroomDTOList = chatService
                .listChatroom(memberDTO)
                .stream()
                .filter(chatroomDTO -> chatroomDTO.getProductDTO().getId().equals(productId))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(chatroomDTOList);
    }

    @RequestMapping(value = "/get/{productId}/{buyerId}", method = RequestMethod.GET)
    public ResponseEntity<ChatroomDTO> getChatRoomByProductAndMember(
            @PathVariable Long productId,
            @PathVariable Long buyerId
    ) {
        ProductDTO productDTO = productService.getProduct(productId);
        MemberDTO buyerDTO = memberService.getMember(buyerId);
        ChatroomDTO chatroomDTO = chatService.getChatroomByProductAndMember(productDTO, buyerDTO);
        return ResponseEntity.ok().body(chatroomDTO);
    }

    @RequestMapping(value = "/get/{roomId}", method = RequestMethod.GET)
    public ResponseEntity<ChatroomDTO> getChatRoom(
            @CurrentMember MemberDTO memberDTO,
            @PathVariable Long roomId
    ) throws Exception {
        ChatroomDTO chatroomDTO = chatService.getChatroom(roomId);
        if(!chatroomDTO
                .getBuyerDTO()
                .getId()
                .equals(memberDTO.getId())
                && !chatroomDTO
                .getProductDTO()
                .getSellerDTO()
                .getId()
                .equals(memberDTO.getId())) {
            throw new Exception("채팅방 접근 권한이 없습니다.");
        }
        return ResponseEntity.ok().body(chatService.getChatroom(roomId));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ChatroomDTO> addChatRoom(
            @CurrentMember MemberDTO memberDTO,
            @RequestBody HashMap<String, String> json
            ) throws Exception {
        Long productId = Long.parseLong(json.get("productId"));
        ProductDTO productDTO = productService.getProduct(productId);
        if(chatService
                .listChatroom(memberDTO)
                .stream()
                .filter(chatroomDTO -> chatroomDTO.getProductDTO().getId().equals(productId) && chatroomDTO.getBuyerDTO().getId().equals(memberDTO.getId()))
                .count() > 0) {
            throw new Exception("이미 채팅방이 존재합니다.");
        }
        if(memberDTO.getId().equals(productDTO.getSellerDTO().getId())) {
            throw new Exception("본인이 등록한 상품은 가격제안으로만 먼저 채팅을 할 수 있습니다.");
        }

        return ResponseEntity.ok().body(chatService.addChatroom(memberDTO, productDTO));
    }

    @RequestMapping(value = "/addFromSuggest", method = RequestMethod.POST)
    public ResponseEntity<ChatroomDTO> addChatRoomFromSuggest(
            @CurrentMember MemberDTO memberDTO,
            @RequestBody HashMap<String, String> json
    ) throws Exception {
        Long productId = Long.parseLong(json.get("productId"));
        Long suggestedMemberId = Long.parseLong(json.get("suggestedMemberId"));
        Integer price = Integer.parseInt(json.get("price"));

        ProductDTO productDTO = productService.getProduct(productId);
        MemberDTO suggestedMember = memberService.getMember(suggestedMemberId);
        if(!productDTO.getSellerDTO().getId().equals(productId)) {
            throw new Exception("가격 제안을 승인할 권한이 없습니다.");
        }
//        if(chatService
//                .listChatroom(memberDTO)
//                .stream()
//                .filter(chatroomDTO -> chatroomDTO.getProductDTO().getId().equals(productId) && chatroomDTO.getBuyerDTO().getId().equals(suggestedMemberId))
//                .count() > 0) {
//            ChatroomDTO chatroomDTO = chatService.getChatroom()
//            throw new Exception("이미 채팅방이 존재합니다.");
//        }
        ChatroomDTO chatroomDTO = chatService.addChatroom(memberDTO, productDTO);
        if(memberDTO.getId().equals(productDTO.getSellerDTO().getId())) {
            chatroomDTO.setPrice(price);
            chatService.updateChatroom(chatroomDTO);
        }
        return ResponseEntity.ok().build() ;
    }

    @RequestMapping(value = "/exist/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Boolean>> checkProductExistChatRoom(
            @CurrentMember MemberDTO memberDTO,
            @PathVariable Long productId
    ) {
        List<ChatroomDTO> chatroomDTOList = chatService
                .listChatroom(memberDTO)
                .stream()
                .filter(chatroomDTO -> chatroomDTO.getProductDTO().getId().equals(productId) && chatroomDTO.getBuyerDTO().getId().equals(memberDTO.getId()))
                .collect(Collectors.toList());
        if(chatroomDTOList.size() > 0) {
            return ResponseEntity.ok().body(Map.of("result", true));
        } else {
            return ResponseEntity.ok().body(Map.of("result", false));
        }
    }

    @RequestMapping(value = "/exist/{productId}/{buyerId}")
    public ResponseEntity<Map<String, Boolean>> checkProductAndBuyerExistChatRoom(
            @CurrentMember MemberDTO memberDTO,
            @PathVariable Long productId,
            @PathVariable Long buyerId
    ) throws Exception {
        ProductDTO productDTO = productService.getProduct(productId);
        if(!productDTO.getSellerDTO().getId().equals(memberDTO.getId())) {
            throw new Exception("권한이 없습니다.");
        }
        MemberDTO buyerDTO = memberService.getMember(buyerId);
        int count = (int) chatService
                .listChatroom(buyerDTO)
                .stream()
                .filter(chatroomDTO -> chatroomDTO.getProductDTO().getId().equals(productId))
                .count();
        return ResponseEntity.ok().body(Map.of("result", count > 0));
    }

    @RequestMapping(value = "/update/{roomId}", method = RequestMethod.POST)
    public ResponseEntity updateChatRoomBySuggest(
            @CurrentMember MemberDTO memberDTO,
            @PathVariable Long roomId,
            @RequestBody Map<String, String> json
    ) throws Exception {
        int price = Integer.parseInt(json.get("price"));
        ChatroomDTO chatroomDTO = chatService.getChatroom(roomId);
        if(!chatroomDTO.getProductDTO().getSellerDTO().getId().equals(memberDTO.getId())) {
            throw new Exception("권한이 없습니다.");
        }
        chatroomDTO.setPrice(price);
        chatService.updateChatroom(chatroomDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/count/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Long>> countProductChatRoom(
            @CurrentMember MemberDTO memberDTO,
            @PathVariable Long productId
    ) {
        long count = chatService
                .listChatroom(memberDTO)
                .stream()
                .filter(chatroomDTO -> chatroomDTO.getProductDTO().getId().equals(productId))
                .count();
        return ResponseEntity.ok().body(Map.of("result", count));
    }

    @RequestMapping(value = "/leave/{roomId}", method = RequestMethod.POST)
    public ResponseEntity leaveChatRoom(
            @CurrentMember MemberDTO memberDTO,
            @PathVariable Long roomId
    ) throws Exception {
        ChatroomDTO chatroomDTO = chatService.getChatroom(roomId);
        if(!isOwnerByChat(chatroomDTO, memberDTO)) {
            throw new Exception("권한이 없습니다.");
        }
        chatService.exitChatroom(chatroomDTO, memberDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/enter/{roomId}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Boolean>> enterChatRoom(
            @CurrentMember MemberDTO memberDTO,
            @PathVariable Long roomId
    ) throws Exception {
        ChatroomDTO chatroomDTO = chatService.getChatroom(roomId);
        if(!isOwnerByChat(chatroomDTO, memberDTO)) {
            throw new Exception("권한이 없습니다.");
        }
        ChatroomDTO updateChatroomDTO = chatService.enterChatroom(chatroomDTO, memberDTO);
        if(updateChatroomDTO == null) return ResponseEntity.ok().body(Map.of("result", false));
        return ResponseEntity.ok().body(Map.of("result", true));
    }
    private boolean isOwnerByChat(ChatroomDTO chatroomDTO, MemberDTO memberDTO) {
        return isBuyerByChat(chatroomDTO, memberDTO) || chatroomDTO.getProductDTO().getSellerDTO().getId().equals(memberDTO.getId());
    }
    private boolean isBuyerByChat(ChatroomDTO chatroomDTO, MemberDTO memberDTO) {
        return chatroomDTO.getBuyerDTO().getId().equals(memberDTO.getId());
    }
}
