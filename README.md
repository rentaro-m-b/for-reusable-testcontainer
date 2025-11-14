# TestcontainersのReusablecontainersの簡易実装
このプロジェクトはTestcontainersのReusableContainersを使う簡単な例示コードです。
使用方法は以下になります。
- TESTCONTAINERS_REUSE_ENABLE=trueという環境変数を定義する
- 本プロジェクトをクローンして手元でmvn clean testを実行する
実行すると、テストで使用したコンテナが起動中のまま手元に残っているはずである。docker psをするとそれを確認できる。
残ってしまったそれは、テスト終わりに停止してしまって良い（ラベルを使うと便利）。
`docker ps --filter label=org.testcontainers -q | xargs docker stop`

# 自前で実装する場合
https://java.testcontainers.org/features/reuse/
上記に記述があるのでそちらを参考に実装することができる。
基本的には以下のことを実施すれば良い
- TESTCONTAINERS_REUSE_ENABLEという環境変数を用意すること
- Testcontainersインスタンス生成時にwithReuse(true)とすること
- @Containerを使わずにコンテナを起動すること
