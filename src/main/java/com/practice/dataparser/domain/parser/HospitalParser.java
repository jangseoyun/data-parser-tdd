package com.practice.dataparser.domain.parser;

import com.practice.dataparser.domain.Hospital;

import java.time.LocalDateTime;

public class HospitalParser implements Parser<Hospital>{

    /*assertEquals(1, hospital.getId()); // col:0
    assertEquals("의원", hospital.getOpenServiceName());//col:1
    assertEquals(3620000,hospital.getOpenLocalGovernmentCode()); // col: 3
    assertEquals("PHMA119993620020041100004",hospital.getManagementNumber()); // col:4
    assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612 //col:5
    assertEquals(1, hospital.getBusinessStatus()); //col:7
    assertEquals(13, hospital.getBusinessStatusCode());//col:9
    assertEquals("062-515-2875", hospital.getPhone());//col:15
    assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); //col:18
    assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());//col:19
    assertEquals("효치과의원", hospital.getHospitalName());//col:21
    assertEquals("치과의원", hospital.getBusinessTypeName());//col:25
    assertEquals(1, hospital.getHealthcareProviderCount()); //col:29
    assertEquals(0, hospital.getPatientRoomCount()); //col:30
    assertEquals(0, hospital.getTotalNumberOfBeds()); //col:31
    assertEquals(52.29, hospital.getTotalAreaSize()); //col:32*/
    @Override
    public Hospital parse(String str) {
        String[] row = str.replace("\"", "").split("\t");
        //System.out.println(Arrays.toString(row));

        Hospital hospital = new Hospital();
        hospital.setId(Long.parseLong(row[0].replace("\"","")));
        hospital.setOpenServiceName(row[1]);
        hospital.setOpenLocalGovernmentCode(Integer.parseInt(row[3]));
        hospital.setManagementNumber(row[4]);

        int year = Integer.parseInt(row[5].substring(0, 4));
        int month = Integer.parseInt(row[5].substring(4, 6));
        int day = Integer.parseInt(row[5].substring(6, 8));
        //System.out.printf("%d %d %d \n", year, month, day);
        hospital.setLicenseDate(LocalDateTime.of(year, month, day, 0, 0));

        hospital.setBusinessStatus(Integer.parseInt(row[7]));
        hospital.setBusinessStatusCode(Integer.parseInt(row[9]));
        hospital.setPhone(row[15]);
        hospital.setFullAddress(row[18]);
        hospital.setRoadNameAddress(row[19]);
        hospital.setHospitalName(row[21]);
        hospital.setBusinessTypeName(row[25]);
        hospital.setHealthcareProviderCnt(Integer.parseInt(row[29]));
        hospital.setPatientRoomCnt(Integer.parseInt(row[30]));
        hospital.setTotalNumberOfBeds(Integer.parseInt(row[31]));
        hospital.setTotalAreaSize(Float.parseFloat(row[32].replace("\"", "")));

        System.out.println(hospital);

        return hospital;
    }
}
