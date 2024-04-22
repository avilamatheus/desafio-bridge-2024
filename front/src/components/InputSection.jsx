import React from 'react';

const InputSection = ({ integerInput, handleInputChange }) => {
  return (
    <>
      <h2 className="mb-4">Enter an integer number (k) to calculate the number of prime positive integers less than k.</h2>
      <div className="input-group mb-3">
        <input
          type="number"
          value={integerInput}
          onChange={handleInputChange}
          className="form-control mx-auto"
          placeholder="Enter an integer number"
          step="1"
          style={{ maxWidth: '250px' }}
        />
      </div>
    </>
  );
};

export default InputSection;
