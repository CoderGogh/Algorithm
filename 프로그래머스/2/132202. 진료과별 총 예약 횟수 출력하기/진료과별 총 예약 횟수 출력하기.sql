-- 코드를 입력하세요
SELECT MCDP_CD AS '진료과코드', COUNT(PT_NO) AS '5월예약건수'
FROM APPOINTMENT
WHERE APNT_YMD LIKE '2022-05-%' -- 어이업슴 취소건은 당연히 빼야지
GROUP BY MCDP_CD
ORDER BY 5월예약건수 ASC, 진료과코드 ASC;
