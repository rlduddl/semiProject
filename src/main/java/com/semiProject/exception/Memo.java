package com.semiProject.exception;



/**
 * 400 (Bad Request) : 클라이언트가 서버로 요청을 잘못 보냈을때, 파라미터 누락, 필드값 누락, 기타 형식에 관련된 오류 throw new BadRequestException(메시지)
 * 
 * 401 (Unauthorized) : 클라이언트가 인증되지 않은상태에서 보호된 리소스에 접근할 때 발생 thorw new UnauthorizedExceptioin(메시지)
 * 
 * 403 (Forbidden) : 클라이언트가 인증은 되었지만 특정한 리소스에 접근 권한이 없을 때 발생 new ForbiddenException(메시지)
 * 
 * 404 (Not Found) : 클라이언트가 요청한 리소스를 찾을 수 없을 때 발생 throw new NotFoundException("요청한 게시물 id " + 1 + "번을 찾을 수 없습니다.")
 * 
 * 405 (Method Not Allowed) : 허용되지 않은 메서드로 접근할때 발생 (예 : GET -> POST) throw new MethodNotAllowedException(메시지)
 * 
 * 409 (Conflict) : 서버에 자료를 요청하는 동안 데이터의 충돌이 발생할 때 (예 : 1번의 값으로 데이터를 요청했는데 1건만 나와야하나 2건이 나왔다..., 1번 데이터를 넣으려고 했는데 이미 있다.) throw new ConfilictException(메시지)
 * 
 * 500 (Internal Server Error) : 서버에서 발생하는 일반적인 예외 상황 (예: 코드를 잘못작성해서 발생하는 오류, null체크, 쿼리 잘못 작성 등등) throw new InternalServerException(메시지)
 * 
 * 503 (Server Unavailable) : 서버가 일시적 과부하 또는 유지보수 상태 또는 서버 다운 throw new ServiceUnavailableException(메시지)
 * 
 */

public class Memo {

}
