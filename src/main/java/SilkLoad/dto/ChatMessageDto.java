package SilkLoad.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    @NotNull
    private Long roomId;

    @NotNull
    private String writer;

    @NotNull
    private Long writerId;

    @NotNull
    private String message;
    /*
    Responst (서버 -> 클라이언트) 로 전달할 때에는 @JsonFormat 을 사용,
    Request(클라이언트 -> 서버)로 전달할 때는 @DateTimeFormat 을 사용한다.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createMessageTime;

}
