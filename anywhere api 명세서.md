<h1 style='background-color: rgba(55, 55, 55, 0.4); text-align: center'>Anywhere API 설계(명세)서</h1>

해당 API 명세서는 '어디든가'의 REST API를 명세하고 있습니다.  

- Domain : <http://localhost:4000> 

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Auth 모듈</h2>

Anywhere 서비스의 인증 및 인가와 관련된 REST API 모듈입니다.
로그인, 회원가입 등의 API가 포함되어 있습니다.  
Auth 모듈은 인증 없이 요청할 수 있습니다. 

- url : /api/v1/auth  

***

#### - 로그인  
  
##### 설명

클라이언트는 사용자 아이디와 평문의 비밀번호를 입력하여 요청하고 아이디와 비밀번호가 일치한다면 인증에 사용될 token과 해당 token의 만료 기간을 응답 데이터로 전달 받습니다. 만약 아이디 혹은 비밀번호가 하나라도 틀린다면 로그인 정보 불일치에 해당하는 응답을 받게됩니다. 네트워크 에러, 서버 에러, 데이터베이스 에러, 토큰 생성 에러가 발생할 수 있습니다.  

- method : **GET**  
- end point : **/sign-in**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userId | String | 사용자의 아이디 | O |
| password | String | 사용자의 비밀번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/auth/sign-in" \
 -d "userId=qwer1234" \
 -d "password=P!ssw0rd"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| accessToken | String | Bearer token 인증 방식에 사용될 JWT | O |
| expiration | Integer | JWT 만료 기간 (초단위) | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "accessToken": "${ACCESS_TOKEN}",
  "expiration": 32400
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 실패 (로그인 정보 불일치)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "SF",
  "message": "Sign in failed."
}
```

**응답 실패 (토큰 생성 실패)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "TCF",
  "message": "Token creation failed."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 아이디 중복 확인  
  
##### 설명

클라이언트는 사용할 아이디를 입력하여 요청하고 중복되지 않는 아이디라면 성공 응답을 받습니다. 만약 아이디가 중복된다면 아이디 중복에 해당하는 응답을 받게됩니다. 네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **POST**  
- end point : **/id-check**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userId | String | 중복확인 할 사용자의 아이디 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/auth/id-check" \
 -d "userId=qwer1234"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (중복된 아이디)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "DI",
  "message": "Duplicated user id."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 전화번호 인증  
  
##### 설명

클라이언트는 숫자로만 이루어진 11자리 전화번호를 입력하여 요청하고 이미 사용중인 전화번호인지 확인 후 4자리의 인증번호를 해당 전화번호에 문자를 전송합니다. 인증번호가 정상적으로 전송이 된다면 성공 응답을 받습니다. 만약 중복된 전화번호를 입력한다면 중복된 전화번호에 해당하는 응답을 받게됩니다. 네트워크 에러, 서버 에러, 데이터베이스 에러, 문자 전송 실패가 발생할 수 있습니다.  

- method : **POST**  
- URL : **/tel-auth**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| telNumber | String | 인증 번호를 전송할 사용자의 전화번호 (11자리 숫자) | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/auth/tel-auth" \
 -d "telNumber=01011112222"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (중복된 전화번호)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "DT",
  "message": "Duplicated user tel number."
}
```

**응답 실패 (인증번호 전송 실패)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "TF",
  "message": "Auth number send failed."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 인증번호 확인  
  
##### 설명

클라이언트는 사용자 전화번호와 인증번호를 입력하여 요청하고 해당하는 전화번호와 인증번호가 서로 일치하는지 확인합니다. 일치한다면 성공에 대한 응답을 받습니다. 만약 일치하지 않는 다면 전화번호 인증 실패에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **POST**  
- end point : **/tel-auth-check**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| telNumber | String | 인증 번호를 확인할 사용자 전화번호 | O |
| authNumber | String | 인증 확인에 사용할 인증 번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/auth/tel-auth-check" \
 -d "telNumber=01011112222" \
 -d "authNumber=1234"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (전화번호 인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "TAF",
  "message": "Tel number authentication failed."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 회원가입  
  
##### 설명

클라이언트는 사용자 이름, 사용자 아이디, 비밀번호, 전화번호, 인증번호, 가입경로, 프로필 이미지, 상태 메세지 입력하여 요청하고 회원가입이 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 만약 존재하는 아이디일 경우 중복된 아이디에 대한 응답을 받고, 만약 존재하는 전화번호일 경우 중복된 전화번호에 대한 응답을 받고, 전화번호와 인증번호가 일치하지 않으면 전화번호 인증 실패에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **POST**  
- end point : **/sign-up**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| profileImage | String | 사용자의 프로필 이미지(기본 이미지) | O |
| name | String | 사용자의 이름 | O |
| userId | String | 사용자의 아이디 | O |
| password | String | 사용자의 비밀번호 (8~13자의 영문 + 숫자) | O |
| address | String | 사용자의 주소 | O |
| telNumber | String | 사용자의 전화번호 (11자의 숫자) | O |
| authNumber | String | 전화번호 인증번호 | O |
| joinPath | String | 회원가입 경로 (기본: 'HOME', 카카오: 'KAKAO', 네이버: 'NAVER', 구글: 'GOOGLE') | O |
| snsId | String | SNS 가입시 sns oauth2 ID | X |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/auth/sign-up"\
 -d "profileImage=/defaultImage"\
 -d "name=홍길동"\
 -d "userId=qwer1234"\
 -d "password=qwer1234"\
 -d "telNumber=01011112222"\
 -d "authNumber=1234"\
 -d "address=부산광역시 부산진구"\
 -d "joinPath=HOME"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (중복된 아이디)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "DI",
  "message": "Duplicated user id."
}
```

**응답 : 실패 (중복된 전화번호)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "DT",
  "message": "Duplicated user tel number."
}
```

**응답 : 실패 (전화번호 인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "TAF",
  "message": "Tel number authentication failed."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***
### 후기 게시판

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Review 모듈</h2>

Anywhere 서비스의 후기 게시판과 관련된 REST API 모듈입니다.  
활동 게시글 목록 보기, 게시글 열람 및 수정, 작성 등의 API가 포함되어 있습니다.  
Active 모듈은 열람 외에는 인증이 필요합니다.  
  
- url : /api/v1/review

***

#### - 활동 게시글 목록 보기
  
##### 설명

클라이언트는 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/**  

##### Request

###### Example

```bash
curl -X GET "http://localhost:4000/api/v1/review"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| reviewPosts | Review[] | 후기 게시글 리스트 | O |
  
**Active**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| review_id | Integer | 후기 게시글 번호 | O |
| review_content | String | 후기 게시글 내용 | O |
| review_writer | String | 작성자 | O |
| review_created_at | String | 작성 날짜 | O |
| review_like_count | Integer | 좋아요 수 | O |
| image_url | ReviewImages[] | 이미지 url 리스트 | O |
| hashtags | String[] | 해시태그 리스트 | O |
| likes | String[] | 좋아요 누른 사용자의 리스트 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "reviewPosts": [
    {
      "reviewId": 1,
      "reviewContent": "여수 갔다왔습니다.",
      "reviewWriter": "qwer1234",
      "reviewCreatedAt": "2024-10-20",
      "reviewLikeCount": 342,
      "imageUrl": [{
        "imageId": 1,
        "reviewId": 1,
        "imageUrl": "http://attractionPlace.jpg",
        "imageOrder": 1 
        }, ...]
      "hashtags": [
        "여수", 
        "국내 여행"],
      "likes": [
        "qwer1234", 
        "asdf1234"]
    },
    ...
  ]
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***

#### - 후기 게시글 등록하기
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **Post**  
- URL : **/**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body

**Active**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| review_content | String | 후기 게시글 내용 | O |
| images | String[] | 이미지 url 리스트 | O |
| hashtags | String[] | 해시태그 리스트 | X |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/review" \
 -h "Authorization=Bearer XXXX" \
 -d "reviewContent": "내용내용내용내용내용내용내용내용내용" \
 -d "images": ["http://attractionPlace.jpg"] \
 -d "hashtags": ["국내 여행"] 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

*** 
#### - 활동 게시글 열람하기
  
##### 설명

클라이언트는 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **GET**  
- URL : **/{reviewId}**  

##### Request

###### Example

```bash
curl -X GET "http://localhost:4000/api/v1/review/1" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
**Review**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| review_id | Integer | 후기 게시글 번호 | O |
| review_content | String | 후기 게시글 내용 | O |
| review_writer | String | 작성자 | O |
| review_created_at | String | 작성일 | O |
| review_like_count | Integer| 좋아요 수 | O |
| image_url | ReviewImages[] | 이미지 url 리스트 | O |
| hashtags | String[] | 해시태그 리스트 | O |
| likes | String[] | 좋아요 누른 사용자의 리스트 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "review": 
    {
      "reviewId": 1,
      "reviewContent": "여수 갔다왔습니다.",
      "reviewWriter": "qwer1234",
      "reviewCreatedAt": "2024-10-20",
      "reviewLikeCount": 342,
      "imageUrl": [{
        "imageId": 1,
        "reviewId": 1,
        "imageUrl": "http://attractionPlace.jpg",
        "imageOrder": 1 
        }, ...]
      "hashtags": [
        "여수", 
        "국내 여행"],
      "likes": [
        "qwer1234", 
        "asdf1234"]
    }
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (존재하지 않는 게시글)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "NRV",
  "message": "No exist review Post"
}
```

**응답 : 실패 (존재하지 않는 이미지)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "NRWI",
  "message": "No exist review image"
}
```

**응답 : 실패 (존재하지 않는 해시태그)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "NRWH",
  "message": "No exist review hashtag"
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***
#### - 후기 게시글 수정하기
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **Patch**  
- URL : **/{reviewId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body

**Active**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| review_content | String | 후기 게시글 내용 | O |
| images | String[] | 이미지 url 리스트 | O |
| hashtags | String[] | 해시태그 리스트 | X |

###### Example

```bash
curl -v -X PATCH "http://localhost:4000/api/v1/review/1" \
 -h "Authorization=Bearer XXXX" \
 -d "reviewContent": "내용내용내용내용내용내용내용내용내용" \
 -d "images": ["http://attractionPlace.jpg"] \
 -d "hashtags": ["국내 여행"] 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (존재하지 않는 게시글)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "NRV",
  "message": "No exist review post"
}
```
**응답 : 실패 (접근 권한 에러러)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8

{
  "code": "NP",
  "message": "No permission"
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 후기 게시글 삭제하기
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **Delete**  
- URL : **/{reviewId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

##### Example

```bash
curl -v -X DELETE "http://localhost:4000/api/v1/review/1" \
 -h "Authorization=Bearer XXXX" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (존재하지 않는 게시글)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "NRV",
  "message": "No exist review Post"
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***
***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>ReviewLike 모듈</h2>

어디든가(Anywhere) 서비스의 ReviewLike와 관련된 REST API 모듈입니다.  
리뷰 게시글 좋아요 생성(취소) API가 포함되어 있습니다.  
ReviewLike 모듈은 모두 인증이 필요합니다.  

- url : /api/v1/review

#### - 리뷰 게시글 좋아요 생성
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  
사용자가 이미 좋아요를 누른 상태라면 좋아요가 취소됩니다.

- method : **POST**  
- URL : **/{reviewId}/like**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -X POST "http://localhost:4000/api/v1/review/1/like/" \
 -h "Authorization=Bearer XXXX"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공 (좋아요 추가)**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "LC",
  "message": "Like clicked."
}
```

**응답 성공 (좋아요 취소)**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "LU",
  "message": "Like unclicked."
}

```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (존재하지 않는 후기 게시글)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRV",
  "message": "No exist review post."
}
```

**응답 : 실패 (존재하지 않는 유저)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NI",
  "message": "No exist user id"
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***
***

***
### 후기 게시판 대댓글 구현
#### - 후기 대댓글 생성
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **POST**  
- URL : **/{reviewId}/comment**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body

| name | description | required |
|---|:---:|:---:|
| review_comment_content | (대)댓글 내용 | O |
| parent_comment_id | 부모 댓글 아이디 | O |

###### Example

```bash
curl -X POST "http://localhost:4000/api/v1/review/1/comments" \
 -h "Authorization=Bearer XXXX"
 -d "reviewCommentContent=멋있어요." \
 -d "parentCommentId=null" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}

```
**응답 실패 (존재하지 않는 사용자 아이디)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NU",
  "message": "No exist user."
}
```

**응답 실패 (존재하지 않는 게시글)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRW",
  "message": "No exist review post."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

#### - 후기 게시글 대댓글 리스트 조회하기
  
##### 설명

클라이언트는 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **GET**  
- URL : **/{reviewId}/comment**  

##### Request

###### Example

```bash
curl -X GET "http://localhost:4000/api/v1/review/1/comment" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| reviewComments | reviewComment[] | 댓글 리스트 | O |  
  
**reviewComments**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| review_comment_id | Integer | 댓글 아이디 | O |
| review_id | Integer | 후기 게시글 아이디 | O |
| review_comment_writer | String | 댓글 작성자 | O |
| review_comment_content | String | 내용 | O |
| review_comment_created_at | String | 작성일 | O |
| parent_comment_id | Integer | 부모 댓글 아이디 | O |
| is_deleted | Boolean | 삭제 여부 | O |
| is_next_comment | Boolean | 다음 댓글 여부 | O |
| depth | Integer | 댓글 깊이 | O |
| order_number | Integer | 댓글 정렬 순서 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "activeComments": [
    {
      "reviewCommentId": 1,
      "reviewId": 1,
      "reviewCommentWriter": "qwer1234",
      "reviewCommentContent": "내용내용내용내용내용내용내용내용내용",
      "reviewCommentCreatedAt": "2024-10-10",
      "parentCommentId": null,
      "isDeleted": false,
      "isNextComment": true,
      "depth": 1,
      "orderNumber": 1
    }, 
    ....
  ]
}
```

**응답 : 실패 (존재하지 않는 게시글)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRW",
  "message": "No exist review Post"
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 후기 게시글 대댓글 수정하기
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **Patch**  
- URL : **/{reviewId}/comment/{reviewCommentId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body
  
| name | type | description | required |
|---|:---:|:---:|:---:|
| review_comment_content | String | 댓글 내용 | O |

###### Example

```bash
curl -v -X PATCH "http://localhost:4000/api/v1/review/1/comment/1" \
 -h "Authorization=Bearer XXXX" \
 -d "reviewCommentContent=저한테 물어보지마세요."
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (존재하지 않는 댓글)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRC",
  "message": "No exist review Comment"
}
```

**응답 : 실패 (존재하지 않는 게시글)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRV",
  "message": "No exist review Post"
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8

{
  "code": "NP",
  "message": "No Permission"
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***
#### - 후기 게시글 대댓글 조회하기
  
##### 설명

클라이언트는 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **GET**  
- URL : **/{reviewId}/comment/{reviewCommentId}**  

##### Request

###### Example

```bash
curl -X GET "http://localhost:4000/api/v1/review/1/comment/1" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| reviewCommentContent | String | 댓글 내용 | O | 

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "reviewCommentContent": "내용내용내용내용내용내용내용내용내용"
}
```

**응답 : 실패 (존재하지 않는 댓글)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRC",
  "message": "No exist review comment"
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 후기 게시글 댓글 삭제하기
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **Delete**  
- URL : **/{reviewId}/comment/{reviewCommentId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

##### Example

```bash
curl -v -X DELETE "http://localhost:4000/api/v1/review/1/comments/1" \
 -h "Authorization=Bearer XXXX" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
}
```

**응답 : 실패 (존재하지 않는 게시글)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRW",
  "message": "No exist review Post"
}
```

**응답 : 실패 (존재하지 않는 댓글)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRC",
  "message": "No exist review Comment"
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8

{
  "code": "NP",
  "message": "No Permission"
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

아마추천

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>RecommendLike 모듈</h2>

어디든가(Anywhere) 서비스의 RecommendLike와 관련된 REST API 모듈입니다.  
추천 게시글 좋아요 생성(취소), 조회 API가 포함되어 있습니다.  
RecommendLike 모듈은 모두 인증이 필요합니다.  
  
- url : /api/v1/recommend

#### - 추천 관광지 좋아요 생성
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **POST**  
- URL : **/attraction/like/{attractionId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -X POST "http://localhost:4000/api/v1/recommend/attraction/like/1" \
 -h "Authorization=Bearer XXXX"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공 (좋아요 추가)**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "LC",
  "message": "Like clicked."
}
```

**응답 성공 (좋아요 취소)**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "LU",
  "message": "Like unclicked."
}

```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (존재하지 않는 관광지)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRA",
  "message": "No exist recommend attraction."
}
```

**응답 : 실패 (존재하지 않는 유저)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NU",
  "message": "No exist user id"
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***

#### - 추천 먹거리 좋아요 생성
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **POST**  
- URL : **/food/like/{foodId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -X POST "http://localhost:4000/api/v1/recommend/food/like/1" \
 -h "Authorization=Bearer XXXX"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공 (좋아요 추가)**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "LC",
  "message": "Like clicked."
}
```

**응답 성공 (좋아요 취소)**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "LU",
  "message": "Like unclicked."
}

```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (존재하지 않는 먹거리)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRF",
  "message": "No exist recommend food."
}
```

**응답 : 실패 (존재하지 않는 유저)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NU",
  "message": "No exist user id"
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***

#### - 추천 미션 좋아요 생성
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **POST**  
- URL : **/mission/like/{missionId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -X POST "http://localhost:4000/api/v1/recommend/mission/like/1" \
 -h "Authorization=Bearer XXXX"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공 (좋아요 추가)**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "LC",
  "message": "Like clicked."
}
```

**응답 성공 (좋아요 취소)**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "LU",
  "message": "Like unclicked."
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (존재하지 않는 미션)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NRM",
  "message": "No exist recommend mission."
}
```

**응답 : 실패 (존재하지 않는 유저)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NU",
  "message": "No exist user id"
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Mypage 모듈</h2>

어디든가(Anywhere) 서비스의 마이페이지과 관련된 REST API 모듈입니다.  
내 정보 보기 및 수정, 내가 쓴 글 목록 보기(후기, 추천), 게시글 수정 및 삭제 등의 API가
포함되어 있습니다.
Mypage 모듈은 모두 인증이 필요합니다.  

- url : /api/v1/mypage

***
#### - 내 정보 조회

##### 설명
이 API는 사용자의 정보를 조회하는 기능을 제공합니다. 
클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청해야 하며, 
인증이 성공하면 사용자 정보가 반환됩니다. 
인증 실패, 네트워크 오류, 서버 오류, 데이터베이스 오류 등이 발생할 수 있습니다. 

- method : **GET**  
- URL : **/api/v1/mypage**

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -X GET "http://localhost:4000/api/v1/mypage" \
 -H "Authorization: Bearer XXXX"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
**mypage**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| user_id | String | 사용자 아이디 | O |
| name | String | 사용자 이름 | O |
| tel_number | String | 사용자 전화번호 | O |
| nickname | String | 사용자 닉네임 | O |
| profile_image | String | 사용자 프로필 이미지 | O |
| is_admin | Boolean | 사용자 관리자 유무 | O |
| user_status | String | 사용자 상태 (예: active, inactive) | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
    "code": "SU",
    "message": "Success.",
    "userId": "qwer1234",
    "telNumber": "01000000000",
    "name": "신짱구",
    "nickname": "신노스케",
    "profileImage": "http://localhost:4000/file/db5e6835-4cc6-443b-a196-216f94e1a280.jpg",
    "isAdmin": true,
    "userStatus": "active"
}
```
**응답 실패 (존재하지 않는 아이디)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NU",
  "message": "No exist user."
}
```


**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

#### - 유저 정보 조회

##### 설명
이 API는 유저의 정보를 조회하는 기능을 제공합니다. 
클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청해야 하며, 
인증이 성공하면 사용자 정보가 반환됩니다. 
인증 실패, 네트워크 오류, 서버 오류, 데이터베이스 오류 등이 발생할 수 있습니다. 

- method : **GET**  
- URL : **/api/v1/mypage/{userId}**

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -X GET "http://localhost:4000/api/v1/mypage/qwer1234" \
 -H "Authorization: Bearer XXXX"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
**mypage**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| user_id | String | 사용자 아이디 | O |
| name | String | 사용자 이름 | O |
| tel_number | String | 사용자 전화번호 | O |
| nickname | String | 사용자 닉네임 | O |
| profile_image | String | 사용자 프로필 이미지 | O |
| is_admin | Boolean | 사용자 관리자 유무 | O |
| user_status | String | 사용자 상태 (예: active, inactive) | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
    "code": "SU",
    "message": "Success.",
    "userId": "qwer1234",
    "telNumber": "01000000000",
    "name": "신짱구",
    "nickname": "신노스케",
    "profileImage": "http://localhost:4000/file/db5e6835-4cc6-443b-a196-216f94e1a280.jpg",
    "isAdmin": true,
    "userStatus": "active"
}
```
**응답 실패 (존재하지 않는 아이디)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NU",
  "message": "No exist user."
}
```


**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

#### - 내 정보 수정
  
##### 설명

이 API는 사용자의 정보를 수정하는 기능을 제공합니다. 
클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청해야 하며, 
인증이 성공하면 사용자 정보가 반환됩니다. 
인증 실패, 네트워크 오류, 서버 오류, 데이터베이스 오류 등이 발생할 수 있습니다. 

- method : **PATCH**  
- URL : **/api/v1/mypage**

##### Request 

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body
| name | type | description | required |
| --- | :---: | :---: | :---: |
| name | String | 사용자 이름 | O |
| nickname | String | 사용자 닉네임 | O |
| profile_image | String | 사용자 프로필 이미지 | O |
| password | String | 사용자 비밀번호 | O |
| telNumber | String | 사용자 전화번호 | O |


###### Example

```bash
curl -X PATCH "http://localhost:4000/api/v1/mypage" \
 -H "Authorization: Bearer XXXX" \
 -d "name=홍길동" \
 -d "nickname=짱구" \
 -d "profile_image=http://localhost:4000/file/db5e6835-4cc6-443b-a196-216f94e1a280.jpg" \
 -d "password=qwer1234" \
 -d "tel_number=01012345678" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (존재하지 않는 사용자)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NU",
  "message": "No exist user."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8

{
  "code": "NP",
  "message": "No permission."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

#### - 전화번호 인증 요청

##### 설명
이 API는 사용자의 전화번호로 인증 요청을 전송하는 기능을 제공합니다. 클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함해야 하며, 성공적으로 요청하면 인증 코드가 발송됩니다.

- method : **PATCH**  
- URL : **/api/v1/mypage/tel-auth**

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| telNumber | String | 인증할 전화번호 | O |

###### Example

```bash
curl -X PATCH "http://localhost:4000/api/v1/mypage/tel-auth" \
 -H "Authorization: Bearer XXXX" \
 -d "tel_number=01012345678"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | application/json | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메시지 | O |

###### Example

**응답 성공**
```json
{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (잘못된 요청)**
```json
{
  "code": "VF",
  "message": "Validation failed."
}
```

---

#### - 전화번호 인증 확인

##### 설명
이 API는 사용자가 받은 인증 코드를 확인하는 기능을 제공합니다. 요청에는 Bearer 토큰이 필요합니다.

- method : **PATCH**  
- URL : **/api/v1/mypage/tel-auth-check**

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| telNumber | String | 인증할 전화번호 | O |
| authCode | String | 인증 코드 | O |

###### Example

```bash
curl -X PATCH "http://localhost:4000/api/v1/mypage/tel-auth-check" \
 -H "Authorization: Bearer XXXX" \
 -d "tel_number=01012345678" \
 -d "auth_number=1234"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | application/json | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메시지 | O |

###### Example

**응답 성공**
```json
{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (인증 실패)**
```json
{
  "code": "AF",
  "message": "Authentication code is invalid."
}
```

---

#### - 비밀번호 수정

##### 설명
이 API는 사용자의 비밀번호를 수정하는 기능을 제공합니다. Bearer 인증 토큰이 필요하며, 현재 비밀번호와 새로운 비밀번호를 입력해야 합니다.

- method : **PATCH**  
- URL : **/api/v1/mypage/update-password**

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| currentPassword | String | 현재 비밀번호 | O |
| newPassword | String | 새 비밀번호 | O |

###### Example

```bash
curl -X PATCH "http://localhost:4000/api/v1/mypage/update-password" \
 -H "Authorization: Bearer XXXX" \
 -d "currentPassword=oldPass123" \
 -d "newPassword=newPass456"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | application/json | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메시지 | O |

###### Example

**응답 성공**
```json
{
  "code": "SU",
  "message": "Success."
}
```

**응답 실패 (비밀번호 불일치)**
```json
{
  "code": "PM",
  "message": "Password mismatch."
}
```

**응답 실패 (인증 실패)**
```json
{
  "code": "AF",
  "message": "Authentication fail."
}
```
***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>HashTag 모듈</h2>

어디든가(Anywhere) 서비스의 인기 해시태그에 관련된 REST API 모듈입니다.  
해시태그의 추가, 삭제 기능의 API가 포함되어 있습니다.
HashTag 모듈은 모두 인증이 필요합니다.  
  
- url : /api/v1/review

***

#### - 해시태그 추가하기

##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **POST**
- URL : **/{reviewId}/hash-tag**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body

**PostHashTag**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| tag_name | String | 해시태그 이름 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/review/hashtag/1" \
 -h "Authorization=Bearer XXXX" \
 -d "tagName": "#힐링" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
}
```

**응답 : 실패 (존재하지 않는 사용자)**
```bash
HTTP/1.1 400 BAD_REQUEST
Content-Type: application/json;charset=UTF-8

{
  "code": "NI",
  "message": "No exist user id."
}
```

**응답 : 실패 (존재하지 않는 후기 게시글)**
```bash
HTTP/1.1 400 BAD_REQUEST
Content-Type: application/json;charset=UTF-8

{
  "code": "NRV",
  "message": "No exist review post."
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

#### - 해시태그 삭제하기

##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **DELETE**  
- URL : **/{reviewId}/hash-tag/{tagId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -v -X DELETE "http://localhost:4000/api/v1/1/hash-tag/1" \
 -h "Authorization=Bearer XXXX" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (존재하지 않는 후기 게시글)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "NRV",
  "message": "No exist review post."
}
```

**응답 : 실패 (존재하지 않는 사용자)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "NU",
  "message": "No exist user."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***

#### - 해시태그 리스트 불러오기

##### 설명

클라이언트는 요청 후 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **GET**  
- URL : **/hash-tag**  

##### Request

###### Header

###### Example

```bash
curl -X GET "http://localhost:4000/api/v1/review/hash-tag" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| hashTags | HashTag[] | 해시태그 리스트 | O |
  
**HashTag**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| tag_name | String | 해시태그 이름 | O |
| usageCount | Integer | 해시태그 사용횟수 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "hashTags": [
    {
        "tagName": "#힐링",
        "useageCount": 20
    },
    ...
  ]
}
```

**응답 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***
***
#### my random
<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>My random 모듈</h2>

Anywhere 서비스의 my random과 관련된 REST API 모듈입니다.  
로그인된 회원의 랜덤 내역 정보 확인 및 삭제 등의 API가 포함되어 있습니다.  
My random 모듈은 모두 인증이 필요합니다.
  
- url : /api/v1/roulette

#### - 내 랜덤 이력 등록하기

##### 설명

클라이언트는 요청 후 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **POST**  
- URL : **/my-random**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Request Body
| name | type | description | required |
|---|:---:|:---:|:---:|
| area_name | String | 지역 이름 | O |
| food_name | String | 먹거리 이름 | O |
| attraction_name | String | 관광지 이름 | O |
| mission_name | String | 미션 이름 | O |

###### Example

```bash
curl -X POST "http://localhost:4000/api/v1/roulette/my-random" \
 -h "Authorization=Bearer XXXX" \
 -d "areaName": "부천" \
 -d "attractionName": "감천 문화마을" \
 -d "foodName": "밀면" \
 -d "missionName": "바다보며 사진찍기"  
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
    "code": "SU",
    "message": "Success."
}
```

**응답 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (존재하지 않는 사용자)**
```bash
HTTP/1.1 400 BAD_REQUEST
Content-Type: application/json;charset=UTF-8

{
  "code": "NI",
  "message": "No exist user id."
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***

#### - 내 랜덤 내역 리스트 삭제하기
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **DELETE**  
- URL : **/my-random/{randomId}**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

##### Example

```bash
curl -v -X DELETE "http://localhost:4000/api/v1/my-random/1" \
 -h "Authorization=Bearer XXXX" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
  
###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
}
```

**응답 : 실패 (존재하지 않는 사용자)**
```bash
HTTP/1.1 400 BAD_REQUEST
Content-Type: application/json;charset=UTF-8

{
  "code": "NI",
  "message": "No exist user id."
}

```

**응답 : 실패 (존재하지 않는 내 랜덤 내역)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "NMR",
  "message": "No exist my random."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
Content-Type: application/json;charset=UTF-8

{
  "code": "NP",
  "message": "No Permission"
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

#### - 내 랜덤 내역 리스트 조회하기
  
##### 설명

클라이언트는 요청 헤더에 Bearer 인증 토큰을 포함하여 요청하고 조회가 성공적으로 이루어지면 성공에 대한 응답을 받습니다. 네트워크 에러, 서버 에러, 유효성 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.  

- method : **GET**  
- URL : **/my-random**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | Bearer 토큰 인증 헤더 | O |

###### Example

```bash
curl -X GET "http://localhost:4000/api/v1/roulette/my-random" \
 -h "Authorization=Bearer XXXX"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |
| myRandoms | MyRandom[] | 내 랜덤 내역 리스트 | O |  
  
**MyRandom**  
| name | type | description | required |
|---|:---:|:---:|:---:|
| random_id | 랜덤 고유 아이디 | O |
| user_id | 유저 아이디 | O |
| area_name | 지역 이름 | O |
| food_name | 먹거리 이름 | O |
| attraction_name | 관광지 이름 | O |
| mission_name | 미션 이름 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "myRandoms": [
    {
        "randomId": 1 
	    "userId": "qwer1234" 
		"areaName": "여수" 
        "attractionName": "이순신광장" 
        "foodName": "이순신버거" 
        "missionName": "여수밤바다를 들으며 밤바다 산책하기"
    },
    {
        "randomId": 2
	    "userId": "qwer1234" 
		"areaName": "광명" 
        "attractionName": "울산과학관" 
        "foodName": "돼지국밥" 
        "missionName": "음식 인증샷 찍기"
    },
   ... 
  ]
}
```

**응답 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
Content-Type: application/json;charset=UTF-8

{
  "code": "AF",
  "message": "Authentication fail."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```
***
<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Area 모듈</h2>

AnyWhere 서비스의 지역 룰렛 관련된 REST API 모듈입니다.
지역 API가 포함되어 있습니다.  
Area 모듈은 인증 없이 요청할 수 있습니다. 
  
- url : /api/v1/area  

***

#### - 지역 가져오기
  
##### 설명

클라이언트는 메인 화면의 클릭 버튼을 눌러 데이터 가져오기 성공 시 응답을 받습니다. 네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- end point : **/**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| areaId | Integer | 지역 번호 | O |
| areaName | String | 지역 이름 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/area" \
 -d "areaId=1" \
 -d "areaName=서울"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "Area": [
    {
      "areaId": 1,
      "areaName": "서울"
    }
    ...
  ]
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Attraction 모듈</h2>

AnyWhere 서비스의 룰렛 관련된 REST API 모듈입니다.
관광지 API가 포함되어 있습니다.  
Attraction 모듈은 인증 없이 요청할 수 있습니다. 
  
- url : /api/v1/attraction  

***

#### - 관광지 가져오기
  
##### 설명

클라이언트는 메인 화면의 클릭 버튼을 눌러 데이터 가져오기 성공 시 응답을 받습니다. 데이터는 지역 룰렛의 지역 번호와 동일한 지역 번호를 가진 데이터를 가져옵니다. 네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- end point : **/**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| areaId | Integer | 지역 번호 | O |
| attractionId | Integer | 관광지 번호 | O |
| attractionName | String | 지역 이름 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/attraction" \
 -d "areaId=1" \
 -d "attractionId" \ 
 -d "areaName=경복궁"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "Attraction": [
    {
      "areaId": 1,
      "attractionId": 1,
      "attractionName": "경복궁"
    }
    ...
  ]
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Food 모듈</h2>

AnyWhere 서비스의 지역 룰렛 관련된 REST API 모듈입니다.
음식 API가 포함되어 있습니다.  
Food 모듈은 인증 없이 요청할 수 있습니다. 
  
- url : /api/v1/food  

***

#### - 음식 가져오기
  
##### 설명

클라이언트는 메인 화면의 클릭 버튼을 눌러 데이터 가져오기 성공 시 응답을 받습니다. 네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- end point : **/**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| foodId | Integer | 음식 번호 | O |
| foodName | String | 음식식 이름 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/food" \
 -d "foodId=1" \
 -d "foodName="라면"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "Food": [
    {
      "foodId": 1,
      "foodName": "라면"
    }
    ...
  ]
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'>Mission 모듈</h2>

AnyWhere 서비스의 지역 룰렛 관련된 REST API 모듈입니다.
지역 API가 포함되어 있습니다.  
Mission 모듈은 인증 없이 요청할 수 있습니다. 
  
- url : /api/v1/mission  

***

#### - 미션 가져오기
  
##### 설명

클라이언트는 메인 화면의 클릭 버튼을 눌러 데이터 가져오기 성공 시 응답을 받습니다. 네트워크 에러, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- end point : **/**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| missionId | Integer | 미션 번호 | O |
| missionName | String | 미션 이름 | O |
| missionContent | String | 미션 설명 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/v1/mission" \
 -d "missinId=1" \
 -d "missionName=바다에서 사진찍기"
 -d "missionContent=바다를 배경으로 전신 사진 찍기"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| Content-Type | 반환되는 Response Body의 Content type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 코드에 대한 설명 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "code": "SU",
  "message": "Success.",
  "Mission": [
    {
      "missionId": 1,
      "missionName": "바다에서 사진찍기",
      "missionContent": "바다를 배경으로 전신 사진 찍기",
    }
    ...
  ]
}
```

**응답 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "code": "VF",
  "message": "Validation failed."
}
```

**응답 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8

{
  "code": "DBE",
  "message": "Database error."
}
```








