import React from 'react';

const URL = "";

function handleHttpErrors(res) {
    if (!res.ok) {
      console.log("error");
      throw {message:res.statusText,status:res.status};
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