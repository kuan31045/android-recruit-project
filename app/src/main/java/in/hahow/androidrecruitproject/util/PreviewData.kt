package `in`.hahow.androidrecruitproject.util

import `in`.hahow.androidrecruitproject.domain.model.Course
import `in`.hahow.androidrecruitproject.domain.model.CourseStatus
import `in`.hahow.androidrecruitproject.domain.model.SuccessCriteria

object PreviewData {
    val courses = listOf(
        Course(
            coverImageUrl = "https://images.api.hahow.in/images/614eca15a39712000619b672",
            numSoldTickets = 30,
            status = CourseStatus.PUBLISHED,
            successCriteria = SuccessCriteria(numSoldTickets = 30),
            title = "學習 AI 一把抓：點亮人工智慧技能樹"
        ),
        Course(
            coverImageUrl = "https://images.api.hahow.in/images/61aa41c75c11460006886198",
            numSoldTickets = 14,
            proposalDueTime = parseToLocalDateTime("2024-01-05T16:00:00.000Z"),
            status = CourseStatus.INCUBATING,
            successCriteria = SuccessCriteria(numSoldTickets = 30),
            title = "療癒動物似顏繪：提升插畫魅力的風格提煉術！"
        ),
        Course(
            coverImageUrl = "https://images.api.hahow.in/images/619fcfd1072d3d0006c4f2f5",
            numSoldTickets = 88,
            proposalDueTime = parseToLocalDateTime("2023-11-05T16:00:00.000Z"),
            status = CourseStatus.SUCCESS,
            successCriteria = SuccessCriteria(numSoldTickets = 30),
            title = "米其林三星主廚教你做！時尚法式甜點的秘密"
        )
    )
}