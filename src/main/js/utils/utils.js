
// let requestHelper = {};

// requestHelper.serverAddress = "http://localhost:3001";

// requestHelper.requestServer = async ({ requestHeaders = {}, requestPath = "/", requestMethod = "GET", requestGetQuery = false, requestPostBody = false } = {}) => {

//     requestHeaders["Content-Type"] = "application/x-www-form-urlencoded;charset=UTF-8";

//     requestPath = `${requestHelper.serverAddress}/${requestPath}`;

//     if (requestGetQuery) {
//         requestPath = requestPath + '?'
//         requestPath = requestPath + Object.keys(requestGetQuery).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(requestGetQuery[key])).join('&');
//     }

//     let fetchOptions = {
//         // Adding headers to the request
//         headers: requestHeaders,
//         // Adding method type
//         method: requestMethod.toUpperCase(),
//         //Adding Cookies as well
//         credentials: "same-origin",
//     }

//     // Adding body or contents to send
//     //body: JSON.stringify(requestPostBody),

//     if (requestPostBody) {
//         let postBodyContent = [];
//         for (let key in requestPostBody)
//             postBodyContent.push(encodeURIComponent(key) + "=" + encodeURIComponent(requestPostBody[key]));
//         postBodyContent = postBodyContent.join("&");
//         fetchOptions.body = postBodyContent
//     }

//     return fetch(requestPath, fetchOptions)
// }


//returns current authentication token
// requestHelper.getData = (key) => {
//     return localStorage.getItem(key) === null ? false : localStorage.getItem(key);
// }

//sets current authentication token
//requestHelper.saveData = (key, value) => { localStorage.setItem(key, value) };


//Check Device Id 
// if (!requestHelper.getData("DEVICEID"))
//     window.electron.getDeviceID((deviceId) => requestHelper.saveData("DEVICEID", deviceId));


export { requestHelper }