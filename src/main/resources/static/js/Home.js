
const start = () => {

}

const toggle = () => {
    if($(".sidebar").is(":visible")){
        $(".sidebar").css("display","none")
        $(".sidebar").css("transition","0.3")
        $(".sidebar").prop("disabled","true")
        $(".bar").css("display","block")
        $(".bar").css("padding-left","2px")
        $(".dashboard_banner").css("padding-left","0%")
    }
    else{
        $(".sidebar").css("display","block")
    }
}

const toggleBar = () => {
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