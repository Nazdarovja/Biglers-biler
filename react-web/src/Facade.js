const URL = "https://stanitech.dk/carrentalapi/api/cars";

function convDate(date) {
  let res
  res = date.substring(9) + '/' + date.substring(5,7) + '/' + date.substring(0,4)
  return res;
}

function handleHttpErrors(res) {
  if (!res.ok) {
    throw { message: res.statusText, status: res.status };
  }
  return res.json();
}

class Facade {

  fetchData = () => {
    const options = this.makeFetchOptions("GET");
    return fetch(URL, options).then(handleHttpErrors);
  }

  fetchCars = (start, end, location) => {
    const options = this.makeFetchOptions("GET");
    const startDate = convDate(start);
    const endDate =  convDate(end);

    if(location.length < 1)
      return fetch(URL + '?start=' + startDate + '&end=' + endDate, options).then(handleHttpErrors);
    else 
      return fetch(URL + '?location=' + location + '&start=' + startDate + '&end=' + endDate, options).then(handleHttpErrors);
  }

  fetchSpecCar = (regno) => {
    const options = this.makeFetchOptions("GET");
    return fetch(URL+"/"+regno, options).then(handleHttpErrors);
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