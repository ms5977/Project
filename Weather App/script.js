const apiKey="df4e5c6e575185d2e89c87836cdc3e5f";
const apiUrl="https://api.openweathermap.org/data/2.5/weather?units=metric&q=";
const searchBox=document.querySelector(".search input");
const searchBtn=document.querySelector(".search button");
const wetherIcon=document.querySelector(".weather-icon");
// const weather=document.querySelector(".weather");


async function checkWeather(city)
{
    const response=await fetch(apiUrl +city+`&appid=${apiKey}`);
    var data =await response.json();
    // console.log(data);
    if (response.status==404) {
        document.querySelector(".error").style.display="block";
        document.querySelector(".weather").style.display="none";
    }
    else
    {

        document.querySelector(".city").innerHTML=data.name;
        document.querySelector(".temps").innerHTML=Math.round(data.main.temp)+"°C";
        document.querySelector(".humidity").innerHTML=data.main.humidity+"%";
        document.querySelector(".wind").innerHTML=data.wind.speed+" Km/hr";
    
        if (data.weather[0].main=="Clouds") 
        {
            wetherIcon.src="images/clouds.png";    
        }
        else if (data.weather[0].main=="Smoke") 
        {
            wetherIcon.src="images/mist.png";    
        }
        else if (data.weather[0].main=="Rain") 
        {
            wetherIcon.src="images/rain.png";    
        }
        else if (data.weather[0].main=="Drizzle") 
        {
            wetherIcon.src="images/drizzle.png";    
        }
        document.querySelector(".weather").style.display="block";
        document.querySelector(".error").style.display="none";
    }
   
}
searchBtn.addEventListener("click",()=>{

    checkWeather(searchBox.value);
})

