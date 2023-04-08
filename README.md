# 데이터 CSV, TSV Parser
> 본 프로젝트는 TSV 데이터 파일을 파싱하고 저장하는 기능으로 nativeQuery 연습을 위해 Spring JdbcTemplate으로 구현했습니다<br>
> 이후 기능은 [전국 병원 위치 데이터 기능 구현](https://github.com/jangseoyun/springjpa-mustache-article)를 참고해주세요.

# 클래스 다이어그램
<img width="1259" alt="image" src="https://user-images.githubusercontent.com/94329274/230710183-b5b40c1e-3383-4060-b45f-6f57ed78e76d.png">

---

# 1. 개발 환경

🟧 BackEnd (Mac)<br>
- Language/Skills: JAVA 11<br>
- Framework: Springboot 2.7.5, Junit5<br>
- Build: Maven 3.1.0<br>
- DB: Mysql<br>
- Library: Lombok, Spring JdbcTemplate<br>
- API 문서 자동화: Swagger Link<br>
- Jacoco
---

# 2. 추출 데이터 상세 내역
> [📝 구현 기능 코드 정리 Link](https://velog.io/@may_yun/Parser-CSV-TSV-%EB%8C%80%EC%9A%A9%EB%9F%89-%EB%8D%B0%EC%9D%B4%ED%84%B0-%ED%8C%8C%EC%8B%B1)

- license_date: 인허가일자<br>
- business_status: 영업상태(1: 영업/정상 2: 휴업 3: 폐업 4: 취소/말소영업상태구분)<br>
- business_status_code: 영업상태코드 (2: 휴업 3: 폐업 13: 영업중)<br>
- business_type_name: 업태구분명<br>
- full_address: 소재지 전체 주소<br>
- healthcare_provider_cnt: 의료인 수<br>
- hospital_name: 병원명<br>
- management_number: 관리번호<br>
- open_local_government_code: 개방자치단체코드<br>
- open_service_name: 개방서비스명<br>
- patient_room_cnt: 입원실 수<br>
- phone: 소재지 전화<br>
- road_name_address: 도로명 주소<br>
- total_area_size: 총 면적<br>
- total_number_of_beds: 병상 수<br>

---

# 3. 구현 기능
> 데이터 파일 파싱 후 DB에 저장하는 기능 구현

- TSV 파일을 [Tab]으로 분리하여 필요한 데이터만 파싱.
( CSV 파일의 경우 ','로 데이터를 분리하기 때문에 데이터 중간에 ','가 존재하는 경우 원하는 데이터 단위로 분리하기 어렵기 때문에 CSV 파일을 TSV 파일로 변환.)

## 3-1) 구현 기능
- 공공데이터 parser 기능 구현을 통해 데이터 가공 
- nativeQuery를 통한 데이터 CRUD

---

# 4. 코드 설명
## 4-1) spring batch를 통해 insert 시간 단축
### [🔗HospitalDao](https://github.com/jangseoyun/data-parser-tdd/blob/main/src/main/java/com/practice/dataparser/dao/HospitalDao.java) / [HospitalParserTest](https://github.com/jangseoyun/data-parser-tdd/blob/main/src/test/java/com/practice/dataparser/domain/parser/HospitalParserTest.java)
111,918건의 데이터를 JPA save 메서드를 통해 DB에 저장했을 때 1,638초 약 28초 걸리던 코드를 Spring batch 적용을 통해 1,161초로 `477초(약 8분)의 시간을 단축`했다.

<img width="760" alt="Pasted Graphic" src="https://user-images.githubusercontent.com/94329274/230639535-5d32f826-b4b0-4684-beb6-784ea9ee3f16.png">
<img width="760" alt="Pasted Graphic 2" src="https://user-images.githubusercontent.com/94329274/230639597-14924963-fd1f-4b3d-a1ba-dd92c04a1540.png">

---

## 4-2) Interface를 통해 parser 및 CRUD 쿼리 추상화
### [🔗Parser](https://github.com/jangseoyun/data-parser-tdd/tree/main/src/main/java/com/practice/dataparser/domain/parser) / [Query](https://github.com/jangseoyun/data-parser-tdd/tree/main/src/main/java/com/practice/dataparser/domain/query)
- 인터페이스를 통해 데이터 파싱 해주는 기능을 정의하고 필요 역할에 따라 parser를 구현 받아 하나의 타입으로 관리한다.
- CrudQuery 인터페이스를 통해 기본적인 등록, 조회, 수정, 삭제, 카운트 메서드를 정의하고 이를 구현 받아 쿼리를 작성할 수 있도록 CrudQuery 인터페이스 하나의 타입으로 관리한다.

---

## 4-3) 제네릭 T 사용으로 코드 재사용
- 클래스 정의 및 리턴 타입에 제네릭을 사용하여 코드 재사용률을 높이고 중복되는 코드 제거
```java
//Ex parser
public interface Parser<T> {
    T parse(String str);
}
```
---

## 4-4) RowMapper를 통해 객체 반환 중복 코드 제거
### [🔗RowMapper](https://github.com/jangseoyun/data-parser-tdd/commit/c30be9b3bd161de6f8ceb52c30b07abb6a27740d)
- 순수 Jdbc를 사용할 때 모든 connection을 직접 연결해야 하는 번거로움과 데이터를 객체로 반환받을 경우
직접 꺼내서 객체로 변환하는 과정을 거쳐야 했다.<br>
- 위의 부분을 RowMapper 내부 메서드를 사용하여 `코드 작성 시간 감소`하고, 객체 반환의 경우 접근 제한자를 private 메서드로 하여 및 `중복되는 코드 제거`했다.<br>
