import React from 'react';

const URL = "http://10.50.130.231:8084/Backend/api/caar";

function handleHttpErrors(res) {
  console.log(JSON.stringify(res))
  console.log(res.ok)
  if (!res.ok) {
    console.log("error");
    throw { message: res.statusText, status: res.status };
  }
  return res.json();
}

class Facade {

  fetchData = () => {
    const options = this.makeFetchOptions("GET");
    return fetch(URL, options).then(handleHttpErrors);
  }

  makeFetchOptions = (type, b) => {
    let headers = {
      'Origin': '',
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }

    return {
      method: type,
      headers,
      body: JSON.stringify(b)
    }
  }
}

const facade = new Facade();
export default facade;