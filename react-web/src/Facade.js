const URL = "https://awha.dk/Backend/api/car";

function convDate(date) {
  let res
  res = date.substring(8) + '/' + date.substring(5, 7) + '/' + date.substring(0, 4)
  return res;
}

function handleHttpErrors(res) {
  console.log("RESPONSE: " + JSON.stringify(res))
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
    const endDate = convDate(end);

    console.log(startDate)
    console.log(endDate)
    console.log(URL + '?start=' + startDate + '&end=' + endDate)

    if (location.length < 1)
      return fetch(URL + '?start=' + startDate + '&end=' + endDate, options).then(handleHttpErrors);
    else
      return fetch(URL + '?location=' + location + '&start=' + startDate + '&end=' + endDate, options).then(handleHttpErrors);
  }

  fetchSpecCar = (regno) => {
    const options = this.makeFetchOptions("GET");
    return fetch(URL + "/regno/" + regno, options).then(handleHttpErrors);
  }

  putCarReservation = (car) => {
    //The API only accepts a list with cars/car, thats why it is packed into an arraylist
    const cars = [];
    cars.push(car);
    const obj = { cars };
    const options = this.makeFetchOptions("PUT", obj);
    fetch(URL + "/" + car.regno, options);
  }

  makeFetchOptions = (type, b) => {
    let headers = {
      // 'Origin': '',
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