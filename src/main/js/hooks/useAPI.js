import { useEffect, useState } from "react";

const useAPI = ({ requestHeaders = {}, requestPath = "/", requestMethod = "GET", requestGetQuery = false, requestPostBody = false } = {}) => {

    const [response, setResponse] = useState([]);
    const [isLoading, setLoading] = useState(true);
    const [error, setError] = useState(false);

    requestHeaders["Content-Type"] = "application/x-www-form-urlencoded;charset=UTF-8";
    requestPath = `${process.env.REACT_APP_API_URL}/${requestPath}`;

    if (requestGetQuery) {
        requestPath = requestPath + '?'
        requestPath = requestPath + Object.keys(requestGetQuery).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(requestGetQuery[key])).join('&');
    }

    const fetchOptions = {
        // Adding headers to the request
        headers: requestHeaders,
        // Adding method type
        method: requestMethod.toUpperCase(),
        //Adding Cookies as well
        credentials: "same-origin",
    }

    if (requestPostBody) {
        let postBodyContent = [];
        for (let key in requestPostBody)
            postBodyContent.push(encodeURIComponent(key) + "=" + encodeURIComponent(requestPostBody[key]));
        postBodyContent = postBodyContent.join("&");
        fetchOptions.body = postBodyContent

    }

    useEffect(() => {
        async function fetchData() {
            try {
                const response=await fetch(requestPath, fetchOptions);
                const jsonResponse = await response.json()
                setResponse(jsonResponse);
            }
            catch (e) {
                setError(e)
            }
            setLoading(false);
        }
        fetchData();
    },[]);
    return [response, isLoading, error];

}


export default useAPI;