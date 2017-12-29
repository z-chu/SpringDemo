package com.zchu.friendbook.controller;

import com.zchu.friendbook.property.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/book")
public class HelloController {


    private final UserProperties user;

    @Autowired
    public HelloController(UserProperties user) {
        this.user = user;
    }


    @GetMapping(path = {"/hello", "/hi"})
    public UserProperties hello() {
        return user;
    }

    @GetMapping(path = "/{id}")
    public String book(@PathVariable Integer id) {
        return "书籍 路径参数  id:" + id;
    }

    @GetMapping
    public String books(@RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "offset", required = false,defaultValue = "0") Integer offset) {
        return "书籍 查询参数  limit:" + limit + "  offset:" + offset;
    }

    @GetMapping(path = "/{bookId}/comments")
    public String bookComments(@PathVariable Integer bookId) {
        return "返回该书(" + bookId + ")所有评论";
    }

    @GetMapping(path = "/{bookId}/comments/{commentId}")
    public String bookComments(@PathVariable Integer bookId, @PathVariable Integer commentId) {
        return "返回该书(" + bookId + ")所有评论中的 :评论(" + commentId + ")";
    }


}
