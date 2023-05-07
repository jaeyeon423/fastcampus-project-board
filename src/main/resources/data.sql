-- 테스트 계정
-- TODO: 테스트용이지만 비밀번호가 노출된 데이터 세팅. 개선하는 것이 좋을 지 고민해 보자.
insert into user_account (user_id, user_password, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('uno', '{noop}asdf1234', 'Uno', 'uno@mail.com', 'I am Uno.', now(), 'uno', now(), 'uno')
;
insert into user_account (user_id, user_password, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('kim423', '{noop}asdf1234', 'jaeyeon', 'uno2@mail.com', 'I am jaeyeon.', now(), 'kim423', now(), 'kim423')
;

-- 123 게시글
insert into article (user_id, title, content, hashtag, created_by, modified_by, created_at, modified_at) values
    ('kim423', 'where is the love', '사랑은 어디에 있나', '#love', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50')
;
