<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/taglib.jsp"%>
<style type="text/css">
    /*通用开始*/
    * {
        font-family: '微软雅黑', Arial, Helvetica, sans-serif;
    }

    a {
        text-decoration: none;
    }

    img {
        border: none;
    }

    li {
        list-style: none;
    }

    body {
        font-size: 12px;
    }

    div {
        font-size: 12px;
    }

    span {
        font-size: 12px;
    }
    /*通用结束*/

    .footer {
        background: #2b2e33;
    }

    .footer .alt {
        overflow: hidden;
        padding: 20px 0;
        width: 1160px;
        margin: auto;
    }

    .footer .alt .foot {
        width: 180px;
        float: left;
    }

    .footer .alt .foot p {
        color: #FFF;
        line-height: 28px;
    }

    .footer .alt .foot a {
        color: #999;
        display: block;
        line-height: 24px;
    }

    .footer .alt .foot a:hover {
        color: #CCC;
    }

    .footer .alt .foot img {
        padding-top: 20px;
    }
</style>

<div class="footer">
    <div class="alt">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div class="foot">
                        <p>公共就业服务平台</p>
                        <a href="https://www.ncss.org.cn/">全国大学生就业平台</a>
                        <a href="http://www.chinajob.gov.cn/">中国就业网</a>
                        <a href="http://www.mohrss.gov.cn/SYrlzyhshbzb/rdzt/gjzzrcfw/">国际组织人才信息服务平台</a>
                        <a href="http://gj.ncss.org.cn">毕业生国际组织实习任职服务平台</a>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="foot">
                        <p>人才招聘网站</p>
                        <a href="http://www.51job.com/">前程无忧</a>
                        <a href="http://www.zhaopin.com/">智联招聘</a>
                        <a href="http://www.chinahr.com/">中华英才网</a>
                    </div>
                </div>
           
                <div class="col-md-3">
                    <div class="foot">
                        <p>联系我们</p>
                        <a>地址：河北省保定市七一东路3027号</a>
                        <a>邮编：071000 </a>
                        <a>E_mail:bdu@bdu.edu.cn</a>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="foot">
                        <p>了解我们</p>
                        <img src="${ctxStatic}/module/include/images/bdxy.jpg" />
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div style="border-top:1px #1F1F1F solid;">
        <p class="alt" style="color:#999; line-height:0;">@ 版权所有：保定学院就业工作处    
            <span style="float:right;">地址：河北省保定市七一东路3027号</span>
        </p>
    </div>

</div>
