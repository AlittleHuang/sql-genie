SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user`
(
    `id`                   int                                                           NOT NULL,
    `pid`                  int                                                           NULL DEFAULT NULL,
    `random_number`        int                                                           NOT NULL,
    `time`                 datetime(6)                                                   NULL DEFAULT NULL,
    `timestamp`            double                                                        NULL DEFAULT NULL,
    `username`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `valid`                bit(1)                                                        NOT NULL,
    `opt_lock`             int                                                           NOT NULL,
    `gender`               int                                                           NULL DEFAULT NULL,
    `instant`              datetime(6)                                                   NULL DEFAULT NULL,
    `test_integer`         int                                                           NULL DEFAULT NULL,
    `test_local_date`      date                                                          NULL DEFAULT NULL,
    `test_local_date_time` datetime(6)                                                   NULL DEFAULT NULL,
    `test_long`            bigint                                                        NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK4x7nfy5oneb661diqq65c4431` (`pid` ASC) USING BTREE,
    CONSTRAINT `FK4x7nfy5oneb661diqq65c4431` FOREIGN KEY (`pid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (0, NULL, 1, '2024-03-16 11:07:57.915000', 1710558477915, 'Marjorie Minnie', b'1', 1, 0,
        '2024-03-19 10:15:55.617000', 32, '2024-03-19', '2024-03-19 10:15:55.617000', 4750678505727327689);
INSERT INTO `user`
VALUES (1, NULL, 14, '2024-03-18 15:46:40.425000', 1710748000425, 'Jeremy Keynes', b'0', 50, 0,
        '2024-03-19 10:15:55.617000', 57, '2024-03-19', '2024-03-19 10:15:55.617000', 7313906094672673586);
INSERT INTO `user`
VALUES (2, NULL, 50, '2024-03-16 16:14:00.693000', 1710576840693, 'Richard Noel', b'1', 42, 1,
        '2024-03-19 10:15:55.617000', 58, '2024-03-19', '2024-03-19 10:15:55.617000', 1080586114820393043);
INSERT INTO `user`
VALUES (3, NULL, 43, '2024-03-18 15:57:53.497000', 1710748673497, 'Aurora Benedict', b'0', 42, 1,
        '2024-03-19 10:15:55.617000', 84, '2024-03-19', '2024-03-19 10:15:55.617000', 7298314988271143570);
INSERT INTO `user`
VALUES (4, NULL, 5, '2024-03-16 06:28:27.355000', 1710541707355, 'Burgess Dodd', b'1', 0, 0,
        '2024-03-19 10:15:55.617000', 49, '2024-03-19', '2024-03-19 10:15:55.617000', 831401180266848975);
INSERT INTO `user`
VALUES (5, NULL, 0, '2024-03-19 01:14:06.296000', 1710782046296, 'Karen Samuel', b'0', 0, 0,
        '2024-03-19 10:15:55.617000', 24, '2024-03-19', '2024-03-19 10:15:55.617000', -4043319611215572235);
INSERT INTO `user`
VALUES (6, NULL, 15, '2024-03-17 13:26:47.080000', 1710653207080, 'Stanley Aldington', b'1', 0, 0,
        '2024-03-19 10:15:55.617000', 65, '2024-03-19', '2024-03-19 10:15:55.617000', 3273807448630242805);
INSERT INTO `user`
VALUES (7, NULL, 8, '2024-03-14 11:40:17.973000', 1710387617973, 'Burke Commons', b'0', 0, 1,
        '2024-03-19 10:15:55.617000', 26, '2024-03-19', '2024-03-19 10:15:55.617000', -5238253615150631805);
INSERT INTO `user`
VALUES (8, NULL, 12, '2024-03-17 00:28:40.090000', 1710606520090, 'Polly Anna', b'1', 0, 1,
        '2024-03-19 10:15:55.617000', 74, '2024-03-19', '2024-03-19 10:15:55.617000', 4272384269299781595);
INSERT INTO `user`
VALUES (9, NULL, 5, '2024-03-19 07:50:16.499000', 1710805816499, 'Vivien Fox', b'0', 0, 0, '2024-03-19 10:15:55.617000',
        70, '2024-03-19', '2024-03-19 10:15:55.617000', -7321451107048196838);
INSERT INTO `user`
VALUES (10, 1, 0, '2024-03-18 13:48:08.114000', 1710740888114, 'Valentine Newton', b'1', 0, 0,
        '2024-03-19 10:15:55.617000', 86, '2024-03-19', '2024-03-19 10:15:55.617000', 6371648974818227557);
INSERT INTO `user`
VALUES (11, 1, 19, '2024-03-14 20:20:36.004000', 1710418836004, 'Bishop Eisenhower', b'0', 0, 0,
        '2024-03-19 10:15:55.617000', 27, '2024-03-19', '2024-03-19 10:15:55.617000', 5639339317193353654);
INSERT INTO `user`
VALUES (12, 1, 8, '2024-03-15 00:47:04.950000', 1710434824950, 'Hedy Titus', b'1', 3, 1, '2024-03-19 10:15:55.617000',
        24, '2024-03-19', '2024-03-19 10:15:55.617000', 8686560328563854428);
INSERT INTO `user`
VALUES (13, 1, 9, '2024-03-17 17:02:29.231000', 1710666149231, 'Elvis Gus', b'0', 0, 1, '2024-03-19 10:15:55.617000',
        92, '2024-03-19', '2024-03-19 10:15:55.617000', -255560755264191135);
INSERT INTO `user`
VALUES (14, 1, 6, '2024-03-18 20:21:18.421000', 1710764478421, 'Baldwin Ingersoll', b'1', 0, 1,
        '2024-03-19 10:15:55.617000', 10, '2024-03-19', '2024-03-19 10:15:55.617000', 1140369362433252061);
INSERT INTO `user`
VALUES (15, 1, 19, '2024-03-18 10:36:01.265000', 1710729361265, 'Elton Lynch', b'0', 0, 1, '2024-03-19 10:15:55.617000',
        93, '2024-03-19', '2024-03-19 10:15:55.617000', -6502065663665352110);
INSERT INTO `user`
VALUES (16, 1, 17, '2024-03-16 03:07:03.564000', 1710529623564, 'Tracy Dewar', b'1', 0, 1, '2024-03-19 10:15:55.617000',
        29, '2024-03-19', '2024-03-19 10:15:55.617000', 58558312681070862);
INSERT INTO `user`
VALUES (17, 1, 4, '2024-03-16 14:04:33.469000', 1710569073469, 'Bess', b'0', 0, 1, '2024-03-19 10:15:55.617000', 22,
        '2024-03-19', '2024-03-19 10:15:55.617000', 3446932300348892221);
INSERT INTO `user`
VALUES (18, 1, 13, '2024-03-18 02:33:05.140000', 1710700385140, 'Quentin Hemingway', b'1', 0, 1,
        '2024-03-19 10:15:55.617000', 13, '2024-03-19', '2024-03-19 10:15:55.617000', -3556287641073669902);
INSERT INTO `user`
VALUES (19, 1, 3, '2024-03-15 13:06:21.635000', 1710479181635, 'Betsy Toynbee', b'0', 0, 1,
        '2024-03-19 10:15:55.617000', 54, '2024-03-19', '2024-03-19 10:15:55.617000', 5166920512141241830);
INSERT INTO `user`
VALUES (20, 2, 17, '2024-03-19 02:55:35.559000', 1710788135559, 'Ulysses Marlowe', b'1', 0, 1,
        '2024-03-19 10:15:55.617000', 45, '2024-03-19', '2024-03-19 10:15:55.617000', 2150454575174774271);
INSERT INTO `user`
VALUES (21, 2, 14, '2024-03-15 00:52:27.344000', 1710435147344, 'Ivy Yule', b'0', 0, 0, '2024-03-19 10:15:55.617000',
        87, '2024-03-19', '2024-03-19 10:15:55.617000', -2380634702726510196);
INSERT INTO `user`
VALUES (22, 2, 16, '2024-03-14 12:29:25.770000', 1710390565770, 'Duke Hobbes', b'1', 0, 0, '2024-03-19 10:15:55.617000',
        84, '2024-03-19', '2024-03-19 10:15:55.617000', 2078794016191796862);
INSERT INTO `user`
VALUES (23, 2, 4, '2024-03-14 11:19:14.498000', 1710386354498, 'Horace Gray', b'0', 0, 0, '2024-03-19 10:15:55.617000',
        0, '2024-03-19', '2024-03-19 10:15:55.617000', -2060197236514499876);
INSERT INTO `user`
VALUES (24, 2, 0, '2024-03-18 23:50:30.489000', 1710777030489, 'Quincy Dolly', b'1', 0, 0, '2024-03-19 10:15:55.617000',
        95, '2024-03-19', '2024-03-19 10:15:55.617000', 1781440584951367375);
INSERT INTO `user`
VALUES (25, 2, 5, '2024-03-17 06:14:58.393000', 1710627298393, 'Marlon Wolf', b'0', 0, 0, '2024-03-19 10:15:55.617000',
        46, '2024-03-19', '2024-03-19 10:15:55.617000', -727907126448561899);
INSERT INTO `user`
VALUES (26, 2, 19, '2024-03-19 04:21:34.388000', 1710793294388, 'Bard Shelley', b'1', 0, 0,
        '2024-03-19 10:15:55.617000', 33, '2024-03-19', '2024-03-19 10:15:55.617000', -6551799888549219624);
INSERT INTO `user`
VALUES (27, 2, 9, '2024-03-17 12:47:59.151000', 1710650879151, 'Flora Dutt', b'0', 0, 1, '2024-03-19 10:15:55.617000',
        94, '2024-03-19', '2024-03-19 10:15:55.617000', 1921009657829238725);
INSERT INTO `user`
VALUES (28, 2, 2, '2024-03-14 13:24:09.396000', 1710393849396, 'Meredith Bush', b'1', 0, 0,
        '2024-03-19 10:15:55.617000', 52, '2024-03-19', '2024-03-19 10:15:55.617000', -7874182259228295677);
INSERT INTO `user`
VALUES (29, 2, 14, '2024-03-17 04:58:46.292000', 1710622726292, 'Erica Sapir', b'0', 0, 1, '2024-03-19 10:15:55.617000',
        42, '2024-03-19', '2024-03-19 10:15:55.617000', 4659913200805362392);
INSERT INTO `user`
VALUES (30, 3, 7, '2024-03-16 05:14:22.697000', 1710537262697, 'Malcolm Hoyle', b'1', 0, 0,
        '2024-03-19 10:15:55.617000', 10, '2024-03-19', '2024-03-19 10:15:55.617000', -7259434428160437443);
INSERT INTO `user`
VALUES (31, 3, 10, '2024-03-15 22:46:04.478000', 1710513964478, 'Merry Howell(s)', b'0', 0, 0,
        '2024-03-19 10:15:55.617000', 37, '2024-03-19', '2024-03-19 10:15:55.617000', 7363156177767928388);
INSERT INTO `user`
VALUES (32, 3, 1, '2024-03-17 01:22:39.799000', 1710609759799, 'Beryl Zangwill', b'1', 0, 1,
        '2024-03-19 10:15:55.617000', 60, '2024-03-19', '2024-03-19 10:15:55.617000', 1937935684253778525);
INSERT INTO `user`
VALUES (33, 3, 17, '2024-03-15 15:48:38.906000', 1710488918906, 'Isaac Ruth', b'0', 0, 0, '2024-03-19 10:15:55.617000',
        95, '2024-03-19', '2024-03-19 10:15:55.617000', -4512307118349830472);
INSERT INTO `user`
VALUES (34, 3, 13, '2024-03-18 04:47:03.335000', 1710708423335, 'Bob Peacock', b'1', 0, 1, '2024-03-19 10:15:55.617000',
        17, '2024-03-19', '2024-03-19 10:15:55.617000', -8831181172611292112);
INSERT INTO `user`
VALUES (35, 3, 0, '2024-03-18 05:47:17.492000', 1710712037492, 'Warner Flower', b'0', 0, 0,
        '2024-03-19 10:15:55.617000', 65, '2024-03-19', '2024-03-19 10:15:55.617000', -8739752644123067962);
INSERT INTO `user`
VALUES (36, 3, 16, '2024-03-18 16:53:42.260000', 1710752022260, 'Hobart Adam', b'1', 0, 0, '2024-03-19 10:15:55.617000',
        23, '2024-03-19', '2024-03-19 10:15:55.617000', 1601641527032885542);
INSERT INTO `user`
VALUES (37, 3, 5, '2024-03-18 07:17:51.992000', 1710717471992, 'Jessie Cook(e)', b'0', 0, 0,
        '2024-03-19 10:15:55.618000', 9, '2024-03-19', '2024-03-19 10:15:55.618000', 2431500496653669815);
INSERT INTO `user`
VALUES (38, 3, 4, '2024-03-18 02:22:08.270000', 1710699728270, 'Fitch I.', b'1', 0, 0, '2024-03-19 10:15:55.618000', 86,
        '2024-03-19', '2024-03-19 10:15:55.618000', 4912937073849769449);
INSERT INTO `user`
VALUES (39, 3, 15, '2024-03-14 17:21:17.305000', 1710408077305, 'Kerr Blake', b'0', 0, 0, '2024-03-19 10:15:55.618000',
        56, '2024-03-19', '2024-03-19 10:15:55.618000', 1737405789993959680);
INSERT INTO `user`
VALUES (40, 4, 18, '2024-03-17 00:23:00.769000', 1710606180769, 'Octavia Eden', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 1, '2024-03-19', '2024-03-19 10:15:55.618000', 7611485393035786485);
INSERT INTO `user`
VALUES (41, 4, 2, '2024-03-14 21:19:11.067000', 1710422351067, 'Denise Barnard', b'0', 0, 0,
        '2024-03-19 10:15:55.618000', 93, '2024-03-19', '2024-03-19 10:15:55.618000', -359475604665364059);
INSERT INTO `user`
VALUES (42, 4, 16, '2024-03-17 02:32:45.896000', 1710613965896, 'Erin Smollett', b'1', 0, 1,
        '2024-03-19 10:15:55.618000', 57, '2024-03-19', '2024-03-19 10:15:55.618000', -7475939964490290180);
INSERT INTO `user`
VALUES (43, 4, 9, '2024-03-17 00:19:54.604000', 1710605994604, 'Nat Gibbon', b'0', 0, 0, '2024-03-19 10:15:55.618000',
        74, '2024-03-19', '2024-03-19 10:15:55.618000', -4698596419152283395);
INSERT INTO `user`
VALUES (44, 4, 17, '2024-03-17 19:15:21.438000', 1710674121438, 'Janet Landon', b'1', 0, 1,
        '2024-03-19 10:15:55.618000', 83, '2024-03-19', '2024-03-19 10:15:55.618000', 4737708291584455641);
INSERT INTO `user`
VALUES (45, 4, 10, '2024-03-17 09:33:48.912000', 1710639228912, 'Rory Crane', b'0', 0, 0, '2024-03-19 10:15:55.618000',
        89, '2024-03-19', '2024-03-19 10:15:55.618000', -3170649044645585372);
INSERT INTO `user`
VALUES (46, 4, 6, '2024-03-16 19:23:28.505000', 1710588208505, 'Patricia Fielding', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 92, '2024-03-19', '2024-03-19 10:15:55.618000', -8982160722688922270);
INSERT INTO `user`
VALUES (47, 4, 11, '2024-03-16 03:19:06.974000', 1710530346974, 'Gale Robinson', b'0', 0, 0,
        '2024-03-19 10:15:55.618000', 38, '2024-03-19', '2024-03-19 10:15:55.618000', 7728083341982140197);
INSERT INTO `user`
VALUES (48, 4, 9, '2024-03-18 16:41:08.186000', 1710751268186, 'Alvis Adams', b'1', 0, 0, '2024-03-19 10:15:55.618000',
        70, '2024-03-19', '2024-03-19 10:15:55.618000', 579469974414704438);
INSERT INTO `user`
VALUES (49, 4, 3, '2024-03-14 15:29:22.805000', 1710401362805, 'Ingrid Meredith', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 91, '2024-03-19', '2024-03-19 10:15:55.618000', -9013692291031076993);
INSERT INTO `user`
VALUES (50, 5, 10, '2024-03-17 07:49:53.629000', 1710632993629, 'Wright Dorothea', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 42, '2024-03-19', '2024-03-19 10:15:55.618000', -876239519066061909);
INSERT INTO `user`
VALUES (51, 5, 12, '2024-03-17 14:06:58.517000', 1710655618517, 'Pearl Judson', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 2, '2024-03-19', '2024-03-19 10:15:55.618000', -7806534445610138950);
INSERT INTO `user`
VALUES (52, 5, 4, '2024-03-17 16:20:49.849000', 1710663649849, 'Eudora Holt', b'1', 0, 0, '2024-03-19 10:15:55.618000',
        73, '2024-03-19', '2024-03-19 10:15:55.618000', -4897251123322007813);
INSERT INTO `user`
VALUES (53, 5, 18, '2024-03-17 22:42:45.265000', 1710686565265, 'Rebecca Emmie', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 56, '2024-03-19', '2024-03-19 10:15:55.618000', 3733502969241853752);
INSERT INTO `user`
VALUES (54, 5, 14, '2024-03-17 10:28:22.024000', 1710642502024, 'Basil Stone', b'1', 0, 0, '2024-03-19 10:15:55.618000',
        60, '2024-03-19', '2024-03-19 10:15:55.618000', -3155864822461456098);
INSERT INTO `user`
VALUES (55, 5, 17, '2024-03-16 08:35:50.060000', 1710549350060, 'Griselda Judith', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 38, '2024-03-19', '2024-03-19 10:15:55.618000', 5511819916028690159);
INSERT INTO `user`
VALUES (56, 5, 8, '2024-03-17 20:25:50.790000', 1710678350790, 'Tyler Edward', b'1', 0, 0, '2024-03-19 10:15:55.618000',
        9, '2024-03-19', '2024-03-19 10:15:55.618000', 5132334712364287471);
INSERT INTO `user`
VALUES (57, 5, 4, '2024-03-14 10:38:53.374000', 1710383933374, 'Wendell Beard', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 81, '2024-03-19', '2024-03-19 10:15:55.618000', 7165312350296033355);
INSERT INTO `user`
VALUES (58, 5, 14, '2024-03-18 14:47:24.524000', 1710744444524, 'Nathaniel Wells', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 1, '2024-03-19', '2024-03-19 10:15:55.618000', -2363934787326157024);
INSERT INTO `user`
VALUES (59, 5, 6, '2024-03-15 20:05:03.651000', 1710504303651, 'Jason Whit', b'0', 0, 0, '2024-03-19 10:15:55.618000',
        89, '2024-03-19', '2024-03-19 10:15:55.618000', -1973458361811752096);
INSERT INTO `user`
VALUES (60, 6, 3, '2024-03-18 07:14:15.338000', 1710717255338, 'Stan Kingsley', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 30, '2024-03-19', '2024-03-19 10:15:55.618000', 1745233639848134413);
INSERT INTO `user`
VALUES (61, 6, 14, '2024-03-18 23:25:38.061000', 1710775538061, 'Boyd Swinburne', b'0', 0, 0,
        '2024-03-19 10:15:55.618000', 53, '2024-03-19', '2024-03-19 10:15:55.618000', 9025047798538950356);
INSERT INTO `user`
VALUES (62, 6, 13, '2024-03-14 18:27:06.269000', 1710412026269, 'Frances MacArthur', b'1', 0, 1,
        '2024-03-19 10:15:55.618000', 61, '2024-03-19', '2024-03-19 10:15:55.618000', -4363849661077553824);
INSERT INTO `user`
VALUES (63, 6, 0, '2024-03-14 19:03:09.953000', 1710414189953, 'Reg Pepys', b'0', 0, 1, '2024-03-19 10:15:55.618000',
        69, '2024-03-19', '2024-03-19 10:15:55.618000', 570868240672665619);
INSERT INTO `user`
VALUES (64, 6, 4, '2024-03-15 06:38:39.669000', 1710455919669, 'Tiffany Austen', b'1', 0, 1,
        '2024-03-19 10:15:55.618000', 6, '2024-03-19', '2024-03-19 10:15:55.618000', -2948982731981471505);
INSERT INTO `user`
VALUES (65, 6, 1, '2024-03-17 09:21:01.445000', 1710638461445, 'Aaron Symons', b'0', 0, 1, '2024-03-19 10:15:55.618000',
        89, '2024-03-19', '2024-03-19 10:15:55.618000', 3619430877029801005);
INSERT INTO `user`
VALUES (66, 6, 19, '2024-03-14 16:30:39.197000', 1710405039197, 'Guy Sweet', b'1', 0, 0, '2024-03-19 10:15:55.618000',
        2, '2024-03-19', '2024-03-19 10:15:55.618000', -4083915836212210150);
INSERT INTO `user`
VALUES (67, 6, 14, '2024-03-18 13:53:06.031000', 1710741186031, 'Rod Morrison', b'0', 0, 0,
        '2024-03-19 10:15:55.618000', 22, '2024-03-19', '2024-03-19 10:15:55.618000', -4640958528615311516);
INSERT INTO `user`
VALUES (68, 6, 18, '2024-03-15 22:15:24.945000', 1710512124945, 'Catherine Hodgson', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 10, '2024-03-19', '2024-03-19 10:15:55.618000', -9203722395074754110);
INSERT INTO `user`
VALUES (69, 6, 3, '2024-03-18 19:34:10.849000', 1710761650849, 'Ingram Charles', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 72, '2024-03-19', '2024-03-19 10:15:55.618000', 7504393291555286746);
INSERT INTO `user`
VALUES (70, 7, 7, '2024-03-17 05:58:35.267000', 1710626315267, 'Gustave Mat(h)ilda', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 27, '2024-03-19', '2024-03-19 10:15:55.618000', 692636356790110081);
INSERT INTO `user`
VALUES (71, 7, 8, '2024-03-17 20:50:57.721000', 1710679857721, 'Wayne Louise', b'0', 0, 1, '2024-03-19 10:15:55.618000',
        1, '2024-03-19', '2024-03-19 10:15:55.618000', -1305611557823045533);
INSERT INTO `user`
VALUES (72, 7, 15, '2024-03-19 02:25:53.886000', 1710786353886, 'Amos Cocker', b'1', 0, 1, '2024-03-19 10:15:55.618000',
        13, '2024-03-19', '2024-03-19 10:15:55.618000', -6322007910336795634);
INSERT INTO `user`
VALUES (73, 7, 19, '2024-03-15 15:38:02.076000', 1710488282076, 'Bruce Archibald', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 96, '2024-03-19', '2024-03-19 10:15:55.618000', -8806536111968364418);
INSERT INTO `user`
VALUES (74, 7, 18, '2024-03-18 18:18:58.640000', 1710757138640, 'Taylor Croft', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 68, '2024-03-19', '2024-03-19 10:15:55.618000', 8639378764557654512);
INSERT INTO `user`
VALUES (75, 7, 6, '2024-03-18 23:10:11.774000', 1710774611774, 'Vera Isabel', b'0', 0, 1, '2024-03-19 10:15:55.618000',
        46, '2024-03-19', '2024-03-19 10:15:55.618000', -7496288643045656675);
INSERT INTO `user`
VALUES (76, 7, 17, '2024-03-16 10:17:26.216000', 1710555446216, 'Cleveland Lindberg(h)', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 86, '2024-03-19', '2024-03-19 10:15:55.618000', 3539038537566386322);
INSERT INTO `user`
VALUES (77, 7, 12, '2024-03-18 21:04:55.457000', 1710767095457, 'Neil Spenser', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 90, '2024-03-19', '2024-03-19 10:15:55.618000', 6439110067399610112);
INSERT INTO `user`
VALUES (78, 7, 8, '2024-03-19 08:30:12.147000', 1710808212147, 'Odelette Richardson', b'1', 0, 1,
        '2024-03-19 10:15:55.618000', 77, '2024-03-19', '2024-03-19 10:15:55.618000', -4037522581948945809);
INSERT INTO `user`
VALUES (79, 7, 6, '2024-03-15 16:20:47.356000', 1710490847356, 'Lester Margery', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 54, '2024-03-19', '2024-03-19 10:15:55.618000', 9214595431809753837);
INSERT INTO `user`
VALUES (80, 8, 19, '2024-03-14 15:10:34.580000', 1710400234580, 'Josephine Child', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 23, '2024-03-19', '2024-03-19 10:15:55.618000', -2351230759966018742);
INSERT INTO `user`
VALUES (81, 8, 9, '2024-03-15 23:25:03.181000', 1710516303181, 'Ralap Zechariah', b'0', 0, 0,
        '2024-03-19 10:15:55.618000', 58, '2024-03-19', '2024-03-19 10:15:55.618000', 6232224976076188060);
INSERT INTO `user`
VALUES (82, 8, 10, '2024-03-16 20:43:41.847000', 1710593021847, 'Lucien Paul', b'1', 0, 1, '2024-03-19 10:15:55.618000',
        72, '2024-03-19', '2024-03-19 10:15:55.618000', -54267072012312900);
INSERT INTO `user`
VALUES (83, 8, 13, '2024-03-17 17:33:38.229000', 1710668018229, 'Max Leopold', b'0', 0, 1, '2024-03-19 10:15:55.618000',
        44, '2024-03-19', '2024-03-19 10:15:55.618000', 8497353992179786947);
INSERT INTO `user`
VALUES (84, 8, 18, '2024-03-16 08:27:29.054000', 1710548849054, 'Jane Lamb', b'1', 0, 0, '2024-03-19 10:15:55.618000',
        89, '2024-03-19', '2024-03-19 10:15:55.618000', 604978409928989875);
INSERT INTO `user`
VALUES (85, 8, 7, '2024-03-14 17:45:45.680000', 1710409545680, 'Boris Kell(e)y', b'0', 0, 0,
        '2024-03-19 10:15:55.618000', 90, '2024-03-19', '2024-03-19 10:15:55.618000', 8302204082533589450);
INSERT INTO `user`
VALUES (86, 8, 18, '2024-03-14 14:07:12.959000', 1710396432959, 'Emma Amelia', b'1', 0, 0, '2024-03-19 10:15:55.618000',
        75, '2024-03-19', '2024-03-19 10:15:55.618000', 2416158680827741498);
INSERT INTO `user`
VALUES (87, 8, 8, '2024-03-18 03:03:28.731000', 1710702208731, 'Monroe Carllyle', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 71, '2024-03-19', '2024-03-19 10:15:55.618000', -5474802366223204556);
INSERT INTO `user`
VALUES (88, 8, 3, '2024-03-16 15:13:51.060000', 1710573231060, 'Truda Alerander', b'1', 0, 1,
        '2024-03-19 10:15:55.618000', 69, '2024-03-19', '2024-03-19 10:15:55.618000', 3919418701582381045);
INSERT INTO `user`
VALUES (89, 8, 4, '2024-03-17 21:09:08.539000', 1710680948539, 'Zara Abraham', b'0', 0, 0, '2024-03-19 10:15:55.618000',
        30, '2024-03-19', '2024-03-19 10:15:55.618000', 2359912441497516440);
INSERT INTO `user`
VALUES (90, 9, 11, '2024-03-15 17:34:46.440000', 1710495286440, 'Zero Tours', b'1', 0, 1, '2024-03-19 10:15:55.618000',
        69, '2024-03-19', '2024-03-19 10:15:55.618000', 3398950805003427255);
INSERT INTO `user`
VALUES (91, 9, 7, '2024-03-18 21:09:40.300000', 1710767380300, 'Julie Swift', b'0', 0, 1, '2024-03-19 10:15:55.618000',
        67, '2024-03-19', '2024-03-19 10:15:55.618000', 4514440859394797944);
INSERT INTO `user`
VALUES (92, 9, 0, '2024-03-15 10:47:45.290000', 1710470865290, 'Archer London', b'1', 0, 1,
        '2024-03-19 10:15:55.618000', 93, '2024-03-19', '2024-03-19 10:15:55.618000', -9002606426889559912);
INSERT INTO `user`
VALUES (93, 9, 16, '2024-03-19 05:05:12.042000', 1710795912042, 'Kennedy Arnold', b'0', 0, 1,
        '2024-03-19 10:15:55.618000', 8, '2024-03-19', '2024-03-19 10:15:55.618000', -1114585979220968259);
INSERT INTO `user`
VALUES (94, 9, 17, '2024-03-18 02:19:35.491000', 1710699575491, 'Abner Lyly', b'1', 0, 1, '2024-03-19 10:15:55.618000',
        79, '2024-03-19', '2024-03-19 10:15:55.618000', 2521819677056556740);
INSERT INTO `user`
VALUES (95, 9, 13, '2024-03-15 05:05:08.846000', 1710450308846, 'Carr Bach', b'0', 0, 1, '2024-03-19 10:15:55.618000',
        54, '2024-03-19', '2024-03-19 10:15:55.618000', -6066783342249357354);
INSERT INTO `user`
VALUES (96, 9, 7, '2024-03-19 06:11:18.277000', 1710799878277, ' Roy Sawyer  ', b'1', 0, 1,
        '2024-03-19 10:15:55.618000', 72, '2024-03-19', '2024-03-19 10:15:55.618000', -1506128766255654305);
INSERT INTO `user`
VALUES (97, 9, 6, '2024-03-17 14:18:55.636000', 1710656335636, 'Nicholas Carroll', b'0', 0, 0,
        '2024-03-19 10:15:55.618000', 90, '2024-03-19', '2024-03-19 10:15:55.618000', -1161006780107664057);
INSERT INTO `user`
VALUES (98, 9, 12, '2024-03-17 01:29:32.811000', 1710610172811, 'Booth Longfellow', b'1', 0, 1,
        '2024-03-19 10:15:55.618000', 41, '2024-03-19', '2024-03-19 10:15:55.618000', 4039447257630764000);
INSERT INTO `user`
VALUES (99, 9, 8, '2024-03-16 17:03:26.179000', 1710579806179, 'Payne Webster', b'0', 0, 0,
        '2024-03-19 10:15:55.618000', 22, '2024-03-19', '2024-03-19 10:15:55.618000', -7399654316814016358);
INSERT INTO `user`
VALUES (100, 10, 5, '2024-03-19 06:02:01.847000', 1710799321847, 'Tony Darwin', b'1', 0, 0,
        '2024-03-19 10:15:55.618000', 0, '2024-03-19', '2024-03-19 10:15:55.618000', -8841438345831085826);

SET FOREIGN_KEY_CHECKS = 1;
