import React, { useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import InputSection from './components/InputSection';
import LoadingButton from './components/LoadingButton';
import ResultSection from './components/ResultSection';

const App = () => {
  const [integerInput, setintegerInput] = useState('');
  const [inputFilled, setInputFilled] = useState(false);
  const [loading, setLoading] = useState(false);
  const [showContent, setShowContent] = useState(true);
  const [result, setResult] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');

  const handleInputChange = (event) => {
    const inputValue = event.target.value;
    if (/^-?\d*$/.test(inputValue)) {
      setintegerInput(inputValue);
      setInputFilled(inputValue !== '');
    }
  };

  const handleCalculateAgain = () => {
    setShowContent(true);
    setResult(null);
    setErrorMessage('');
  };

  const handleSubmit = async () => {
    setLoading(true);
    setShowContent(false);

    try {      
      const response = await fetch(import.meta.env.VITE_BACK_END_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ k: integerInput })
      });
      
      if (response.ok) {
        const data = await response.json();
        setResult(data);
        setErrorMessage('');
      } else {
        const errorData = await response.json();
        setErrorMessage(errorData.errorMessage);
        setResult(null);
      }
    } catch (error) {
      console.log(error);
      setErrorMessage('The request could not be made, please try again');
      setResult(null);
    }

    setLoading(false);
  };

  return (
    <div className="container-fluid bg-dark d-flex justify-content-center align-items-center vh-100">
      <div className="container text-center text-light">
        <h1 className="display-4 mb-4">Desafio Bridge 2024 - Matheus Avila</h1>
        {
          showContent && <InputSection integerInput={integerInput} handleInputChange={handleInputChange} />
        }
        {
          loading ? (<LoadingButton />) : (showContent && (<button onClick={handleSubmit} className={`btn btn-primary ${!inputFilled ? 'btn-secondary' : ''}`} disabled={!inputFilled}>Calculate</button>))
        }
        {
          !loading && !showContent && (<ResultSection integerInput={integerInput} result={result} errorMessage={errorMessage} handleCalculateAgain={handleCalculateAgain} />)
        }
      </div>
    </div>
  );
};

export default App;
