# 구름 (LEVEL.2) 카드 모으기
## part. 구현, Hash

### 문제
---

구름이는 카드를 모으는 게임을 한다. 카드는 1번 카드부터 N번 카드까지 총 N종의 카드가 있다.
카드 모으기 게임은 구름이에게 M장의 카드들이 순서대로 제공되고, 구름이는 이 카드를 수집하여 N종의 카드를 모두 모으면 게임에서 승리할 수 있다.

구름이는 최대한 빨리 게임에서 승리하려고 한다. 구름이가 게임에서 승리할 수 있는 조건을 충족하기 위해서 최소 몇 장의 카드를 받아야 하는지 구하시오. 만약 M 장의 카드를 모두 받아도 모든 종류의 카드를 모을 수 없다면 -1 을 출력한다.

### 입력
---

첫째 줄에 두 정수 N, M이 공백을 두고 주어진다.
다음 M개의 줄에는 구름이에게 제공되는 카드 종류의 번호가 한 줄에 하나씩 주어진다. 구름이가 카드를 받는 순서대로 주어진다.

- 1 ≤ N, M ≤ 1 000 000
- 카드 종류로 주어지는 번호는 1 이상 N 이하의 정수이다.

### 출력
---
구름이가 게임에서 승리할 수 있는 조건을 충족하기 위해 받아야 하는 카드의 최소 장수를 출력한다. 만약 M 장의 카드를 모두 받아도 모든 종류의 카드를 모을 수 없다면 -1 을 출력한다.

### 예시 1
#### 입력
```
4 10
2
1
1
1
1
4
3
1
4
2
```
#### 출력
```
7
```

### 예시 2
#### 입력
```
6 10
4
5
6
5
2
1
4
5
6
1
```
#### 출력
```
-1
```


🎯 설명이 많이 필요하지 않은 문제이므로 java파일에 주석으로 설명 했습니다.