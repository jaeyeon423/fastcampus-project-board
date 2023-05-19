-- 테스트 계정
-- TODO: 테스트용이지만 비밀번호가 노출된 데이터 세팅. 개선하는 것이 좋을 지 고민해 보자.
insert into user_account (user_id, user_password, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('uno', '{noop}asdf1234', 'Uno', 'uno@mail.com', 'I am Uno.', now(), 'uno', now(), 'uno')
;
insert into user_account (user_id, user_password, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('kim423', '{noop}asdf1234', 'jaeyeon', 'uno2@mail.com', 'I am jaeyeon.', now(), 'kim423', now(), 'kim423')
;

-- 123 게시글
insert into article (user_id, title, content, hashtag, category, created_by, modified_by, created_at, modified_at) values
    ('kim423', '유머 게시글1', '유머 게시글', '#hashtag', 'humor', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', '유머 게시글2', '유머 게시글', '#hashtag', 'humor', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', '유머 게시글3', '유머 게시글', '#hashtag', 'humor', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', '유머 게시글4', '유머 게시글', '#hashtag', 'humor', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', '유머 게시글5', '유머 게시글', '#hashtag', 'humor', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),

    ('kim423', '언론의 수작질', '사랑은 어디에 있나', '#hashtag', 'politics', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', '이러니 욕을 먹지', '사랑은 어디에 있나', '#hashtag', 'politics', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', '경제 게시글3', '사랑은 어디에 있나', '#hashtag', 'politics', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', '경제 게시글4', '사랑은 어디에 있나', '#hashtag', 'politics', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', '경제 게시글5', '사랑은 어디에 있나', '#hashtag', 'politics', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),

    ('kim423', 'where is the love', '사랑은 어디에 있나', '#hashtag', 'economy', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', 'where is the love', '사랑은 어디에 있나', '#hashtag', 'society', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50'),
    ('kim423', 'where is the love', '사랑은 어디에 있나', '#hashtag', 'sports', 'Kamilah', 'Murial', '2021-05-30 23:53:46', '2021-03-10 08:48:50')
;
