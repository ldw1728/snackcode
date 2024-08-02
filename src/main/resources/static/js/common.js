const JWT_TOKEN_KEY = 'jwttkn'
const STORED_PATH   = 'storedPath'

NodeList.prototype.setAttrAll = function(key, value) {
    this.forEach((node, idx, listObj)=>{
        node.setAttribute(key, value);
    });
}

function isExistElement(selector){
    return document.querySelector(selector) || false;
}

/**
 * 특정 셀렉터들에 속성을 부여한다.
 * @param selector
 * @param key
 * @param value
 */
function setAttrAll(selector, key, value) {
    document.querySelectorAll(selector).forEach(el => {
        el.setAttribute(key, value);
    })
}

function isShowElement(selector){
    return !document.querySelector(selector).classList.contains('hidden');
}

/** 요소 표시 */
function showElement(selector) {
    document.querySelectorAll(selector).forEach(el => {
        el.classList.remove('hidden');
    });
}

/** 요소 숨기기 */
function hiddenElement(selector) {
    document.querySelectorAll(selector).forEach(el => {
        el.classList.add('hidden');
    });
}

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
function addClickEvnt(selector, func, options){
    document.querySelectorAll(selector).forEach(el => {
        el.addEventListener('click', (e) => {
            func = func.bind(e);
            func();
        }, options);
    })
}

const domReady = func => {

    if(document.readyState === 'loading'){
        document.addEventListener("DOMContentLoaded", func);
    }
    else{
        func();
    }

}