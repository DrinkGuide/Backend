<img width="1100" alt="image" src="https://github.com/user-attachments/assets/d40a05ce-96ef-40f5-9390-b53e23cb6685" />

# 📢 Voice Label 서비스

Voice Label은 시각 장애인 사용자가 편의점 제품을 식별하고 이미지 인식을 통해 제품의 상세 정보와 영양 정보를 음성 피드백으로 제공하는 웹 서비스입니다.

## 🖥️ 시연 영상
<img src="https://github.com/user-attachments/assets/9743237e-f2b3-4b50-a405-e699f885e1b2" alt="GIFMaker_me" width="300" height="500">

<p>GIF라 음성이 지원되지 않습니다. 해당 링크로 접속하시면 음성도 들으실 수 있습니다.  
<a href="https://drive.google.com/file/d/1DBa2M4zI_sDo0h-2ZKqR_bXOh7tNMekf/view?usp=sharing" target="_blank">여기 클릭</a></p>


<br />

## 📎 기능

- **Google 로그인 이용**: Google 로그인 API를 사용하여 사용자 인증 및 프로필 접근을 용이하게 함
- **제품 식별**: Teachable Machine 이미지 분류를 사용하여 제품을 인식하고, 화면을 두 번 터치하면 구매 기록으로 데이터를 전송
- **음성 피드백**: `react-speech-recognition`을 사용하여 음성으로 피드백을 말하면 Speech to Text를 거쳐 서버에 저장
- **결제 통합**: `tosspayments 결제 api`를 사용하여 결제 처리
- **기록 및 구독 관리**: 사용자 프로필을 통해 구매 기록과 구독을 관리
<br/>

<p align="center">
  <img src="https://github.com/user-attachments/assets/85317998-cffd-4a0b-be23-87f61ea47c1e" width="180" height="300" />
  <img src="https://github.com/user-attachments/assets/72c588d9-a10b-4427-83f9-f5cf7ca81fb0" width="180" height="300" />
  <img src="https://github.com/user-attachments/assets/bf073cc9-8fc0-47f8-bf6d-e92b1661c301" width="180" height="300" />
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/09e90608-400a-4c75-80e0-d0d7a8af58c4" width="180" height="300" />
  <img src="https://github.com/user-attachments/assets/bc603520-9071-4124-97d7-e38bcd479d11" width="180" height="300" />
  <img src="https://github.com/user-attachments/assets/11fe2349-25df-4024-98d2-257de09cce45" width="180" height="300" />
</p>

## 📜 페이지

- **로그인 페이지**: Google 로그인을 사용하여 사용자 인증을 허용합니다.
- **메인 페이지**: 로그인 후 홈페이지입니다.
- **스캔 페이지**: 카메라 입력을 사용한 제품 스캔.
- **피드백 페이지**: 사용자가 제품 정보 정확성에 대한 피드백을 제공할 수 있습니다.
- **마이 페이지**: 구독 세부 정보와 사용자의 구매 기록이 포함됩니다.

## ⚙️ 기술

### 프론트엔드

- **react-webcam**: 웹캠 기능을 처리합니다.
- **Teachable Machine 이미지 분류**: Teachable Machine-Google에서 학습시킨 가중치 결과 파일을 프로젝트에 넣어놓고 웹캠으로 들어오는 이미지를 분류
- **react-speech-recognition**: 음성 인식 기능을 제공합니다.
- **Text to Speech**: 외부 라이브러리 없이 기본적으로 구현됩니다.
- **상태 관리**: `recoil`을 사용하여 로그인 및 구독 상태를 관리합니다.

### 백엔드

- **Spring JPA**: 데이터 관리를 위해 Spring Data JPA를 사용
- **MySQL (RDS)**: 데이터베이스는 Amazon RDS를 활용한 MySQL을 사용
- **Swagger**: API 문서화.
- **Actuator**: Health Check
- **Spring Security/OAuth2.0 Client & OAuth2.0 Google**: Spring Security로 소셜 로그인을 통해 간편 인증을 처리했고 JWT로 인가 처리
- **Toss Payments 결제 API - test**: 토스 페이먼츠 결제 API 연동
- **개발 및 클라우드 아키텍처**</br>
  <img width="689" alt="image" src="https://github.com/user-attachments/assets/8c86b837-9522-4926-91fd-6e17fef462c0" />



## 팀 소개

- **기획**: 김리사
- **디자인**: 최성희
- **프론트엔드**: 김수아, 노경인
- **백엔드 + AI + 클라우드**: 이주승, 박진홍


## 📌 설치

Node.js가 기기에 설치되어 있는지 확인한 후, 프로젝트 디렉토리에서 다음 명령어를 실행하세요:

```bash
npm install
npm install --save react-speech-recognition
npm install @reduxjs/toolkit react-redux
npm install axios
npm install jwt-decode
npm install --save react-scroll
npm install @tosspayments/payment-sdk --save
npm install recoil
```

애플리케이션 실행
애플리케이션을 시작하려면 프로젝트 디렉토리로 이동하여 다음을 실행하세요:

```bash
npm start
```
선호하는 브라우저에서 서버 주소로 이동하여 배포를 확인하세요.

## ☑️ 테스트
모든 기능이 다양한 브라우저에서 예상대로 작동하는지 확인하세요, 특히 카메라 접근 및 음성 인식 기능을 확인합니다.
