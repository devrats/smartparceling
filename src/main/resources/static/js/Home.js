let isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);

const start2 = () => {
    if (isMobile) {
        let spans = document.getElementsByTagName("link")[2]
        spans.remove()
    } else{
        let spans = document.getElementsByTagName("link")[1]
        spans.remove()
    }
}

const start1 = () => {
    if (isMobile) {
        let spans = document.getElementsByTagName("link")[2]
        spans.remove()
    } else{
        document.getElementById("banneres").className = "login container text-center"
        let spans = document.getElementsByTagName("link")[1]
        spans.remove()
    }
}

const start = () => {
    if (isMobile) {
        $(".sidebar").css("display","none")
        $(".bar").css("display","block")
        $(".dashboard_banner").css("padding-left","0px")
        let spans = document.getElementsByTagName("link")[2]
        spans.remove()
    } else{
        $(".sidebar").css("display","block")
        $(".bar").css("display","none")
        document.getElementById("banneres").className = "dashboard_banner container text-center"
        let spans = document.getElementsByTagName("link")[1]
        spans.remove()
    }
}

const toggle = () => {
    if(isMobile){
        if($(".sidebar").is(":visible")){
            $(".sidebar").css("display","none")
            $(".sidebar").prop("disabled","true")
            $(".bar").css("display","block")
        }
        else{
            $(".sidebar").css("display","block")
        }
    } else {
        if($(".sidebar").is(":visible")){
            $(".sidebar").css("display","none")
            $(".sidebar").prop("disabled","true")
            $(".bar").css("display","block")
            $(".bar").css("padding-left","2px")
            $(".dashboard_banner").css("padding-left","0%")
        }
        else{
            $(".sidebar").css("display","block")
        }
    }
}

const toggleBar = () => {
    if(isMobile){
        if($(".sidebar").is(":visible")){
            $(".sidebar").css("display","none")
            $(".bar").prop("disabled",true)
        }
        else{
            $(".sidebar").css("display","block")
            $(".bar").css("display","none")
        }
    } else{
        if($(".sidebar").is(":visible")){
            $(".sidebar").css("display","none")
            $(".sidebar").css("transition","0.3")
            $(".bar").prop("disabled",true)
        }
        else{
            $(".sidebar").css("display","block")
            $(".bar").css("display","none")
            $(".dashboard_banner").css("padding-left","9%")
        }
    }
}