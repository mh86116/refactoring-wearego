# Travel With Guide
태국 여행 가이드 매칭 플랫폼

## Git flow
### Branches
1. master : 사용자 웹으로 배포 중인 브랜치
2. develop : 다음 버전으로 배포할 브랜치
3. hotfix : 배포 중인 브랜치에서 발생한 버그를 해결하는 브랜치
4. feature : 기능을 개발하는 브랜치

#### 브랜치 전략
- master → master
- develop → develop(부모브랜치 : master)
- hotfix → hotfix/\<issue number\> (부모브랜치 : master)
- feature → feature /\<issue number\>/짧은 설명 (부모브랜치 : develop)


※ 완료 후 부모브랜치에 병합한다.