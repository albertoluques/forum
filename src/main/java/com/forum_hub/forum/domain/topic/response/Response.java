package com.forum_hub.forum.domain.topic.response;

import com.forum_hub.forum.domain.topic.Topic;
import com.forum_hub.forum.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "responses")
@Entity(name = "Response")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private LocalDateTime dateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_response_id")
    private User userResponse;
    private Boolean solved;

    //method
    public Response(String message, User author, Topic topic) {
        this.message = message;
        this.topic = topic;
        this.userResponse = author;
        this.dateTime = LocalDateTime.now();
        this.solved = false;
    }

    public User userResponse() {
        return userResponse;
    }

    public void setSolved() {
        this.solved = !solved;
    }
    public void updateMessage(String message) {
        this.message = message;
    }
}
