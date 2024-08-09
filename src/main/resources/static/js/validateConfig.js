
const ALL_CONSTRAINTS = {
    email : {
        presence: {
            allowEmpty : false,
            message: '이메일은 필수입력 항목입니다.'
        },
        email: {
            message: '이메일 형식으로 입력해 주세요.'
        }
    },
    password: {
         presence: {
            allowEmpty : false,
            message: '비밀번호는 필수입력 항목입니다.'
         },
    },
    editPassword: {
        ...this.password,
        format : {
            pattern: '^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&^])[A-Za-z\\d$@$!%*#?&^]{8,20}$',
            message: '영문, 숫자, 특수문자 포함 8~20자리로 입력해 주세요.'
        }
    },
    confirmPassword:{
        equality: {
            attribute: "password",
            message: "비밀번호를 확인해 주세요.",
            comparator: function(v1, v2) {
                return  v1 === v2;
            }
        }
    },
    name: {
        presence: {
            allowEmpty : false,
            message: '이름은 필수입력 항목입니다.'
        }
    },
    title: {
        presence: {
            allowEmpty : false,
            message: '제목은 필수입력 항목입니다.'
        }
    },
    codeCntns:{
        presence: {
            allowEmpty : false,
            message: '컨텐츠는 필수입력 항목입니다.'
        }
    },
    loginEmail:{

    }
};

const LOGIN_CONSTRAINTS = {
    email   : ALL_CONSTRAINTS.email,
    password: ALL_CONSTRAINTS.password
}

const JOIN_CONSTRAINTS = {
    email   : ALL_CONSTRAINTS.email,
    password: ALL_CONSTRAINTS.editPassword,
    confirmPassword: ALL_CONSTRAINTS.confirmPassword,
    name    : ALL_CONSTRAINTS.name
};

const DETAIL_CONSTRAINTS = {
    title: ALL_CONSTRAINTS.title,
    codeCntns: ALL_CONSTRAINTS.codeCntns,
}

/** form validation */
function validateForm(form, names, constraints = ALL_CONSTRAINTS){

    return new Promise((resolve, reject) => {

        if(!Array.isArray(names)){
            reject(new Error('[names] parameter of validateForm function is not array..'));
        }

        let paramObj = {};

        names.forEach(name => {
            paramObj[name] = form.querySelector(`[name=${name}]`).value;
        });
        //console.log(paramObj)

        let resultObj = validate(paramObj, constraints, {fullMessages: false});

        showVaidMsg(resultObj);

        if(resultObj){
            //console.log(resultObj)
            reject(new Error("exist invalid form"));
            return;
        }
        resolve(resultObj);
    });

}

/** valid msg 표시 */
function showVaidMsg(resultObj = {}){

    const VALID_NAME = 'validation_msg';

    removeElementsAll(`[name=${VALID_NAME}]`);

    const validationMsgEl = (msg) => {

        let spanElement = document.createElement('span');
        spanElement.classList.add('mt-1', 'text-sm', 'text-red-400');
        spanElement.innerText = msg;
        spanElement.setAttribute('name', VALID_NAME);

        return spanElement;
    }

    Object.keys(resultObj)?.forEach((name, idx) => {

        let el = document.querySelector(`[name=${name}]`);

        if(el){
            el.parentElement.insertAdjacentElement('afterend', validationMsgEl(resultObj[name][0]))
        }

    });

}