# 모두의 레로트 프로젝트

## 구조
### Member
엔티티
- id
- email
- nickname
- name
- password
- phone
- role

로직
- 회원가입
- 로그인
- 팔로우, 언팔로우
- 레포트 > 쓰기, 수정, 삭제
- 레포트 > 좋아요, 싫어요
- 레포트 > 댓글

### MemberFollow
엔티티
- id
- followerId
- leaderId

### Report
엔티티
- id
- title
- content
- stockId
- memberId
- recommendation (매수, 매도, 중립)
- reportTags

### ReportReaction
엔티티
- id
- reportId
- memberId
- reactionType (LIKE, UNLIKE)

### ReportTag
엔티티
- id
- reportId
- value (ex: 삼성전자, 반도체, HBM)

### ReportReply
엔티티
- id
- memberId
- reportId

로직
- 조회, 생성, 수정, 삭제

## TODO
- [ ] 한국예탁결제원 주식 API 연동

