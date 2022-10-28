package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TableInfoDto {

    private int getCountAll;
    private List<UserDto> userList;

}
