# aws-lambda-ex

- Java 17
- AWS API Gateway
- AWS Lambda
- AWS S3
- AWS DynamoDB

![undefined](https://github.com/alanhakhyeonsong/aws-lambda-ex/assets/60968342/89373339-e2d5-4f60-8915-70b827f1c0da)

- Serverless로 실행되는 AWS Lambda의 function들은 Java로 패키징된 jar내의 각 Handler를 통해 실행한다.
- jar 파일은 AWS S3 bucket 내에 배포되고 Lambda의 각 function들과 공용으로 연결된다.
- Handler는 AWS DynamoDB와 연결되어 예제 데이터를 Create, Read 한다.
- AWS API Gateway를 RESTful API로 배포하여 Lambda의 각 handler와 매핑하여 처리한다.

API 호출 예시 응답은 다음과 같다.

![image](https://github.com/alanhakhyeonsong/aws-lambda-ex/assets/60968342/2f4e7a67-23c2-413e-97f0-29043cd77101)