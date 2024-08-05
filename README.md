# 프리온보딩 백엔드 인턴십 선발과제
<br>

## 서비스 개요
- 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.
<br>

## ERD
![erd](https://github.com/user-attachments/assets/69948d03-e269-42d6-8eaa-34fb54c812db)
- `회사`, `사용자`, `채용공고`, `지원내역` 4가지 테이블 정의

<br>

## 1. 채용공고 등록
- `JobPostingRequest` 생성하여 등록에 사용
  - `채용포지션`, `채용보상금`, `채용내용`, `사용기술`, `국가`, `지역`
- 요청 : `POST` /jobposting/{companyId}

## 2. 채용공고 수정
- `JobPostingRequest`를 등록에 사용
- 요청 : `PUT` /jobposting/{companyId}/{jobPostingId}

## 3. 채용공고 삭제
- `DeleteJobPostingRequest` 생성하여 삭제할 `채용공고_id`를 받음
- 요청 : `DELETE` /jobposting

## 4-1. 채용공고 목록
- `JobPostingResponse` 생성하여 요구된 데이터를 가져옴
  - `채용공고_id`, `회사명`, `국가`, `지역`, `채용포지션`, `채용보상금`, `사용기술`
- 요청 : `GET` /jobposting
- 반환값 : 채용공고 전체 목록

## 4-2. 채용공고 검색
- `JobPostingResponse`를 사용
- `회사명`, `국가`, `지역`, `채용포지션`, `채용보상금`, `사용기술` 내용 중 `검색키워드`와 일치하는 채용공고 목록을 가져오는 쿼리 작성
-  요청 : `GET` /jobposting/search?keyword=`검색키워드`
-  반환값 : 채용공고 검색 결과 목록

## 5. 채용공고 상세 페이지
- `JobPostingDetailResponse` 생성하여 `채용내용`과 `회사가올린다른채용공고` 데이터까지 가져옴
  - `채용공고_id`, `회사명`, `국가`, `지역`, `채용포지션`, `채용보상금`, `사용기술`, `채용내용`, `회사가올린다른채용공고`
- 해당 회사가 올린 다른 채용공고를 가져오기 위해 `회사_id`가 일치하면서 `채용공고_id`가 일치하지 않는 채용공고 목록을 가져오는 쿼리 작성
- 요청 : `GET` /jobposting/{jobPostingId}
- 반환값 : 채용공고 상세 목록

## 6. 사용자는 채용공고 지원
- `ApplicationRequest` 생성하여 지원하는 `사용자_id`와 지원할 `채용공고_id`를 받음
- 요청 : `POST` /application

<br>

## Unit Test
- **ApplicationControllerTest**, **JobPostingControllerTest**로 테스트 진행
