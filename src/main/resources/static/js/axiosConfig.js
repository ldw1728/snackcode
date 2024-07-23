
/** request interceptors */
axios.interceptors.request.use((config) => {

    // 요청 전 실행

    // jwt token
    //const token = window.localStorage.getItem(JWT_TOKEN_KEY);
    //config.headers.Authorization = token;

    return config;
}, (error => {
    // 요청 에러 시 실행
    // ....
    return Promise.reject(error);
}));

/** response interceptors */
axios.interceptors.response.use((response) => {
    // 200 응답 데이터 처리
    // ....
    console.info(response);
    return response;
}, (error => {
    // 200 외 응답 처리


    return Promise.reject(error);
}));