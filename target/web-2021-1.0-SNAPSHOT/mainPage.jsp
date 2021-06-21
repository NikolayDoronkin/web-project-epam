<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="intro_fragment.jsp" charEncoding="utf-8"/>
<html>
<c:import url="head.jsp" charEncoding="utf-8"/>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<main class="main">
    <section class="intro" id="home">
        <div class="wrapper">
            <div class="intro_wrapper">
                <div class="intro_main_all">
                    <h1 class="intro_title">
                        &#60;EPAM&#62;
                    </h1>
                    <h1 class="intro_title_second">
                        CAFE
                    </h1>
                    <p class="intro_subtitle">
                        10:00 - 19:00
                    </p>
                    <div class="intro_days">
                        <div class="intro_week">
                            <ul class="intro_list_day">
                                <li class="intro_day_item"><fmt:message key="local.intro.monday"/></li>
                                <li class="intro_day_item"><fmt:message key="local.intro.tuesday"/>
                                </li>
                                <li class="intro_day_item"><fmt:message key="local.intro.wednesday"/>
                                </li>
                                <li class="intro_day_item"><fmt:message key="local.intro.thursday"/>
                                </li>
                                <li class="intro_day_item"><fmt:message key="local.intro.friday"/>
                                </li>
                                <li class="intro_day_item"><fmt:message key="local.intro.saturday"/>
                                </li>
                                <li class="intro_day_item"><fmt:message key="local.intro.sunday"/>
                                </li>
                            </ul>
                        </div>
                        <div class="intro_img_week">
                            <ul class="intro_list_day">
                                <li class="intro_day_img">
                                    <img class="intro_img intro_img_newvalue" src="./resources/circle_week.png"
                                         alt="Title">
                                </li>
                                <li class="intro_day_img">
                                    <img class="intro_img intro_img_newvalue" src="./resources/circle_week.png"
                                         alt="Title">
                                </li>
                                <li class="intro_day_img">
                                    <img class="intro_img intro_img_newvalue" src="./resources/circle_week.png"
                                         alt="Title">
                                </li>
                                <li class="intro_day_img">
                                    <img class="intro_img intro_img_newvalue" src="./resources/circle_week.png"
                                         alt="Title">
                                </li>
                                <li class="intro_day_img">
                                    <img class="intro_img intro_img_newvalue" src="./resources/circle_week.png"
                                         alt="Title">
                                </li>
                                <li class="intro_day_img">
                                    <img class="intro_img intro_img_newvalue" src="./resources/circle_weekend.png"
                                         alt="Title">
                                </li>
                                <li class="intro_day_img">
                                    <img class="intro_img intro_img_newvalue" src="./resources/circle_weekend.png"
                                         alt="Title">
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="intro_cards">

                    <figure class="intro_card intro_card_size_lg">
                        <img src="./resources/cafe1.jpg" alt="Enjoy" class="intro_card_img intro_card_img_size_lg">
                        <figcaption class="intro_card_title">
                            <fmt:message key="local.intro.enjoy"/>
                        </figcaption>
                    </figure>

                    <figure class="intro_card intro_card_size_lg">
                        <img src="./resources/cafe2.jpg" alt="food" class="intro_card_img intro_card_img_size_lg">
                        <figcaption class="intro_card_title">
                            <fmt:message key="local.intro.food"/>
                        </figcaption>
                    </figure>

                    <figure class="intro_card intro_card_size_sm">
                        <img src="./resources/cafe3.jpg" alt="With" class="intro_card_img intro_card_img_size_sm">
                        <figcaption class="intro_card_title">
                            <fmt:message key="local.intro.with"/>
                        </figcaption>
                    </figure>

                    <figure class="intro_card intro_card_size_sm">
                        <img src="./resources/cafe4.jpg" alt="EPAM" class="intro_card_img intro_card_img_size_sm">
                        <figcaption class="intro_card_title">
                            <fmt:message key="local.intro.EPAM"/>
                        </figcaption>
                    </figure>

                    <figure class="intro_card intro_card_size_sm">
                        <img src="./resources/cafe5.jpg" alt="CAFE" class="intro_card_img intro_card_img_size_sm">
                        <figcaption class="intro_card_title">
                            <fmt:message key="local.intro.CAFE"/>
                        </figcaption>
                    </figure>

                </div>
            </div>
        </div>

    </section>
    <section class="benefits">
        <div class="benefits_wrapper">
            <h2 class="benefits_title">
                <fmt:message key="local.benefits.label"/>
            </h2>
            <div class="benefits_cards">
                <div class="benefits_card">
                    <div class="benefits_card_pic">
                        <img src="./resources/building.png" alt="Title" class="benefits_card_img">
                    </div>
                    <h3 class="benefits_card_title">
                        <fmt:message key="local.benefits.sublabel.first"/>
                    </h3>
                    <p class="benefits_card_desc">
                        <fmt:message key="local.benefits.desc.first"/>
                    </p>
                </div>
                <div class="benefits_card">
                    <div class="benefits_card_pic">
                        <img src="./resources/take-away-food.png" alt="Title" class="benefits_card_img">
                    </div>
                    <h3 class="benefits_card_title">
                        <fmt:message key="local.benefits.sublabel.second"/></h3>
                    <p class="benefits_card_desc">
                        <fmt:message key="local.benefits.desc.second"/>
                    </p>
                </div>
                <div class="benefits_card">
                    <div class="benefits_card_pic">
                        <img src="./resources/speedometer.png" alt="Title" class="benefits_card_img">
                    </div>
                    <h3 class="benefits_card_title">
                        <fmt:message key="local.benefits.sublabel.third"/>
                    </h3>
                    <p class="benefits_card_desc">
                        <fmt:message key="local.benefits.desc.third"/>
                    </p>
                </div>
            </div>
        </div>
    </section>
    <section class="contacts" id="contact">
        <div class="contacts_wrapper">
            <div class="contacts_label">
                <h2 class="contacts_label_text">
                    <fmt:message key="local.contact.label"/>
                </h2>
            </div>
            <div class="contacts_label_desc_box">
                <div>
                    <p class="contacts_label_desc">
                        <fmt:message key="local.contact.content"/>
                    </p>
                </div>
                <div class="contacts_label_desc_text">
                    <a href="" class="contacts_label_desc_text">
                        +375-29-820-99-12
                    </a>
                </div>
                <div class="contacts_label_desc_text">
                    <a href="" class="contacts_label_desc_text">
                        +375-29-266-26-00
                    </a>
                </div>
                <div>
                    <p  class="contacts_label_desc">
                        <fmt:message key="local.contact.find"/>
                    </p>
                </div>
                <p href="" class="contacts_label_desc_text">
                    г. Минск, ул. Купревича, 3В
                </p>
                <p href="" class="contacts_label_desc_text">
                    г. Минск, ул. Притыцкого 27а
                </p>
                <div class="contacts_maps">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2349.187122352363!2d27.683212915860498!3d53.928419380105105!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcebb6d7b5ae9%3A0xc3063ef9bbdf17cf!2z0YPQuy4g0JDQutCw0LTQtdC80LjQutCwINCa0YPQv9GA0LXQstC40YfQsCAz0LIsINCc0LjQvdGB0LogMjIwMTQx!5e0!3m2!1sru!2sby!4v1620131224250!5m2!1sru!2sby" width="400" height="300" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2350.363437086331!2d27.48435971655412!3d53.90751738010037!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbc549a1f211f5%3A0x3b52530ef3138fb7!2z0YPQuy4g0J_RgNC40YLRi9GG0LrQvtCz0L4gMjcsINCc0LjQvdGB0Lo!5e0!3m2!1sru!2sby!4v1620131259585!5m2!1sru!2sby" width="400" height="300" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                </div>
            </div>
        </div>
    </section>
</main>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>
