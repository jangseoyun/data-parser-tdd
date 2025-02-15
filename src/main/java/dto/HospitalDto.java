package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class HospitalDto {
    //1병원 이름, 2주소, 3도로명주소, 4의료진 수, 5병상 수, 6면적
    //7. 2: 휴업 3: 폐업 13: 영업중
    private Long id;
    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private int healthcareProviderCnt;
    private int totalNumberOfBeds;
    private float totalAreaSize;
    private String businessStatusCode;
}
