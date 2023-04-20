package com.example.munjeongminbackend.domain.member.present

import com.example.munjeongminbackend.domain.member.present.dto.MemberAddRequest
import com.example.munjeongminbackend.domain.member.service.MemberAddService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController (
        private val memberAddService: MemberAddService
) {

    @PostMapping("/{id}")
    fun add(@PathVariable("id") id: Long, @RequestBody request: MemberAddRequest) {
        memberAddService.execute(id, request)
    }
}