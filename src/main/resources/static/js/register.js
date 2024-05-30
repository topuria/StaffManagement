document.getElementById('registerForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username, password})
    })
        .then(response => {
            if (response.ok) {
                alert('Registration successful!');
                window.location.href = 'login.html';
            } else {
                alert('Registration failed.');
            }
        })
        .catch(error => console.error('Error:', error));
});
