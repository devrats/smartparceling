let isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);

connect()
const start2 = () => {
    if (isMobile) {
        let spans = document.getElementsByTagName("link")[2]
        spans.remove()
    } else {
        let spans = document.getElementsByTagName("link")[1]
        spans.remove()
    }
}

const start1 = () => {
    if (isMobile) {
        let spans = document.getElementsByTagName("link")[2]
        spans.remove()
    } else {
        document.getElementById("banneres").className = "login container text-center"
        let spans = document.getElementsByTagName("link")[1]
        spans.remove()
    }
}

const start = () => {
    if (isMobile) {
        $(".sidebar").css("display", "none")
        $(".bar").css("display", "block")
        $(".dashboard_banner").css("padding-left", "0px")
        let spans = document.getElementsByTagName("link")[2]
        spans.remove()
        $('.table').dataTable({
            "order": []
        });
    } else {
        $(".sidebar").css("display", "block")
        $(".bar").css("display", "none")
        document.getElementById("banneres").className = "dashboard_banner container text-center"
        let spans = document.getElementsByTagName("link")[1]
        spans.remove()
    }
}

const toggle = () => {
    if (isMobile) {
        if ($(".sidebar").is(":visible")) {
            $(".sidebar").css("display", "none")
            $(".sidebar").prop("disabled", "true")
            $(".bar").css("display", "block")
        } else {
            $(".sidebar").css("display", "block")
        }
    } else {
        if ($(".sidebar").is(":visible")) {
            $(".sidebar").css("display", "none")
            $(".sidebar").prop("disabled", "true")
            $(".bar").css("display", "block")
            $(".bar").css("padding-left", "2px")
            $(".dashboard_banner").css("padding-left", "0%")
        } else {
            $(".sidebar").css("display", "block")
        }
    }
}

const toggleBar = () => {
    if (isMobile) {
        if ($(".sidebar").is(":visible")) {
            $(".sidebar").css("display", "none")
            $(".bar").prop("disabled", true)
        } else {
            $(".sidebar").css("display", "block")
            $(".bar").css("display", "none")
        }
    } else {
        if ($(".sidebar").is(":visible")) {
            $(".sidebar").css("display", "none")
            $(".sidebar").css("transition", "0.3")
            $(".bar").prop("disabled", true)
        } else {
            $(".sidebar").css("display", "block")
            $(".bar").css("display", "none")
            $(".dashboard_banner").css("padding-left", "9%")
        }
    }
}

function myFunction() {
    let x = document.getElementById("exampleInputPassword1");
    if (x.type === "password") {
        x.setAttribute('type', 'text')
    } else {
        x.setAttribute("type", "password")
    }
}

const myFunction1 = () => {
    let y = document.getElementById("exampleInputPassword2");
    if (y.type === "password") {
        y.setAttribute('type', 'text')
    } else {
        y.setAttribute("type", "password")
    }
}
const payment = () => {
    let amount = $("#payment_amount").val();
    console.log(amount)
    $.ajax(
        {
            url: '/user/pay',
            data: JSON.stringify({amount: amount}),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success: function (response) {
                if (response.status == "created") {
                    let options = {
                        "key": "rzp_test_4sgOUR0hFBHwtn",
                        "amount": response.amount,
                        "currency": "INR",
                        "name": "Devvrat",
                        "description": "Recharge your account with Pika coins",
                        "order_id": response.id,
                        "handler": function (response) {
                            //alert(response.razorpay_payment_id);
                            //alert(response.razorpay_order_id);
                            //alert(response.razorpay_signature)
                            paymentSuccess(response.razorpay_payment_id, response.razorpay_order_id, "success")
                        },
                        "prefill": {
                            "name": "",
                            "email": "",
                            "contact": ""
                        },
                        "notes": {
                            "address": "137/A balaji puram, agra"

                        },
                        "theme": {
                            "color": "#3399cc"
                        }
                    };
                    let rzp1 = new Razorpay(options);
                    rzp1.on('payment.failed', function (response) {
                        alert(response.error.code);
                        alert(response.error.description);
                        alert(response.error.source);
                        alert(response.error.step);
                        alert(response.error.reason);
                        alert(response.error.metadata.order_id);
                        alert(response.error.metadata.payment_id);
                    });
                    rzp1.open();

                }
            }
        }
    )
};

function paymentSuccess(razorpay_payment_id, razorpay_order_id, paid) {
    $.ajax(
        {
            url: '/user/paySuccess',
            data: JSON.stringify({
                "razorpay_payment_id": razorpay_payment_id
                , "razorpay_order_id": razorpay_order_id, "status": paid
            }),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success: function (response) {
                swal.fire(
                    'Good job!',
                    'payment successful',
                    'success'
                )
            },
            error: function (error) {
                console.log(error)
                swal.fire(
                    'error',
                    'payment successful but take some time to complete',
                    'error'
                )
            }
        }
    )
}

function showOrderRecieved() {
    if ($("#order_receive").is(":visible")) {
        $("#order_receive").css("display", "none")
    } else {
        $("#order_receive").css("display", "block")
    }
}

function sizeError() {
    swal.fire(
        'error',
        'Photo identity proof file exceed given limit',
        'error'
    )
    location.replace("/user/dashboard/1")
}

function profileUpload() {
    console.log("hello world")
    Swal.fire({title: 'Update profile picture?', showCancelButton: true}).then(result => {
        if (result.value) {
            $('#profilepicture').css("display", "block")
        } else {

        }
    })
}

function hideProPic() {
    $('#profilepicture').css("display", "none")
}

const toggleChat = () => {
    $(".chat-option").toggle()
}

//  chat optimization

function connect() {
    let socket = new SockJS('/user/server/')
    stompClient = Stomp.over(socket)
    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/user/queue/messages", function (response) {
            showMessage(JSON.parse(response.body))
        });
    })
}


function showMessage(message) {
    let value = '#' + message.name + 'table'
    if (message.name=='Server'){
        $(value).prepend(`<span style="color: red"> <b><i>${message.name} : </i></b>${message.text}</span> <br>`)
    } else{
        $(value).prepend(`<span style="color: mintcream"> <b><i>${message.name} : </i></b>${message.text}</span> <br>`)
    }
}

function showMessage1(message,url) {
    let value = '#' + url + 'table'
    $(value).prepend(`<span style="color: burlywood"> <b><i>${message.name} : </i></b>${message.text}</span> <br>`)
}

function chatting(element) {
    let name = element.id
    let value = '#' + name.substr(0, name.length) + '1'
    $(".chatWindow").hide("slow")
    $(value).show("slow")
}

    function backArrow (element) {
        $(".chatWindow").show("slow")
        let name = element.parentNode.id
        let value = '#' + name
        $(value).hide("slow")
    }


    function send(element) {
        let name = element.parentNode.parentNode.firstElementChild.id
        let value = '#' + name
        let message = $(value).val()
        let jasonOb = {
            name: $("#user-name").text(),
            text:message
        }
        let urls = element.parentNode.parentNode.parentNode.id
        urls = urls.substr(0,urls.length-1)
        localStorage.setItem("urls",urls)
        showMessage1(jasonOb,urls)
        stompClient.send("/chatting/message/" + localStorage.getItem("urls"),{},JSON.stringify(jasonOb))
    }