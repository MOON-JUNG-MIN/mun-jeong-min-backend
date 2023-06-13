package com.example.munjeongminbackend.domain.chat.present

import com.example.munjeongminbackend.domain.chat.present.dto.MessageRealRequest
import com.example.munjeongminbackend.domain.chat.present.dto.element.RoomDataResponse
import com.example.munjeongminbackend.domain.chat.present.dto.list.RoomListResponse
import com.example.munjeongminbackend.domain.chat.service.RoomService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/room")
class RoomController (
        private val roomService: RoomService
) {

    @GetMapping
    fun readAll(): RoomListResponse {
        return roomService.allRoom()
    }

    @GetMapping("/{id}")
    fun readOne(@PathVariable("id") id: Long): RoomDataResponse {
        return roomService.oneRoom(id)
    }

    @PostMapping("/{id}")
    fun send(@RequestBody request: MessageRealRequest, @PathVariable("id") id: Long) {
        roomService.send(request, id)
    }
}