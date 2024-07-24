const JWT_TOKEN_KEY = 'jwttkn'

function setXScroll(selector){
    document.querySelector(selector).addEventListener('wheel', (e)=>{
        if (e.deltaY > 0) {
            document.querySelector(selector).scrollBy(10, 0)

        }
        else{
            document.querySelector(selector).scrollBy(-10, 0);
        }
    })
}

/** 특정 셀렉터의 모든 요소 삭세 */
function removeElementsAll(selector){
    document.querySelectorAll(selector).forEach(el => {
        el.remove();
    });
}

/** click event */
function addClickEvnt(selector, func){
    document.querySelector(selector).addEventListener('click', func);
}

const domReady = func => {

    if(document.readyState === 'loading'){
        document.addEventListener("DOMContentLoaded", func);
    }
    else{
        func();
    }

}