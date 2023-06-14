# word-diary

## 1. 개요

- 매일 마음에 떠오른 단어를 기록하는 앱
- 서비스 주소 : [word-diary.com](https://word-diary.com)

### 1.1 사용 기술

`Java` , `SpringBoot` , `Spring Data JPA` , `Thymeleaf` ,  `MySQL` , `AWS ElasticBeanstalk`

### 1.2 주요 화면

![주요화면.png](./docs/img/주요화면2.png)

---

## 2. 배포

### 2.1 배포 과정

- jar 빌드 `./gradlew clean build` /build/libs 하위 경로에 jar 파일 생성됨
- VPC 리소스 사전 구성 (ElasticBeanstalk구성 시 기본 VPC 사용하지 않게 하기 위함)  
  VPC ⇒ IGW ⇒ Subnet ⇒ RoutingTable 순으로 생성하였음
- 키페어 생성
- ElasticBeanstalk(이하 EB) 애플리케이션, 환경 구성
  사용자 지정 옵션을 선택하여 앞서 구성한 VPC & 키페어 지정, DB 사용자 정보 등 입력
- 환경 구성 시작. 수 분 소요.
- 로컬 PC에서 DB, EC2에 접근할 수 있게 커스텀 Security Group 생성
  SSH, HTTP, HTTPS, MySQL(TCP 3306) 허용
- RDS에 퍼블릭 접근 허용, 커스텀 SG 추가 후 즉시 적용
- MySQL 스키마, 테이블 생성 (/doc/DDL.sql 참고)
- EB에 환경속성 추가 (application.yml에서 사용될 것임)  
  RDS_HOSTNAME , RDS_PASSWORD , RDS_PORT , RDS_SCHEMA_NAME , RDS_USERNAME , SPRING_PROFILES_ACTIVE
- EC2에 커스텀 SG 적용 후 ssh 접속 가능여부 및 로그 확인  
  `/var/log/nginx/access.log`  
  `/var/log/nginx/error.log`  
  `/var/log/web.stdout.log`  
  `/var/log/eb-engine.log` 
- 앱 서버 다시 시작. 서비스 확인.
- 도메인 구입. 레코드 추가. Route53을 사용해 EB를 지정한다면 별칭을 사용할 것. 레코드 유형은 A-IPv4
- SSL 인증서(ACM) 적용. 기존 HTTP80 리스너는 HTTPS443으로 리다이렉트


### 2.2 주의
- default VPC, default subnet 절대 삭제하지 말자. default subnet 없으면 EB 구성 시 오류 발생. 콘솔에서 다시 생성할 수 없어 CLI를 쓰거나 SupportCase 등록 필요.
- [CLI를 이용한 default VPC 생성 방법](https://docs.aws.amazon.com/vpc/latest/userguide/default-vpc.html#create-default-vpc)

---
