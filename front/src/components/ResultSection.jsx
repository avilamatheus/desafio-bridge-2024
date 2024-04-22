import React from 'react';

const ResultSection = ({ integerInput, result, errorMessage, handleCalculateAgain }) => {
  return (
    <>
      <div className="mt-3">
        <h3>Your input: {integerInput}</h3>
      </div>
      {result ? (
        <div className="mt-3">
          <h3>Result:</h3>
          <h5>Quantity of Prime Numbers: {result.result}</h5>
          <h5>Time Elapsed (seconds): {result.timeElapsed}</h5>
        </div>
      ) : (
        <div className="mt-3">
          <h3>Error: {errorMessage}</h3>
        </div>
      )}
      <button onClick={handleCalculateAgain} className="btn btn-primary mt-3">Calculate again</button>
    </>
  );
};

export default ResultSection;
