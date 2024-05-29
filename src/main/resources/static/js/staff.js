document.addEventListener('DOMContentLoaded', function () {
    // Function to populate the departments dropdown
    function populateDepartments() {
        fetch('http://localhost:8090/api/department')
            .then(response => response.json())
            .then(departments => {
                const departmentSelect = document.getElementById('departmentSelect');
                departments.forEach(department => {
                    const option = document.createElement('option');
                    option.value = department.id;
                    option.text = department.name;
                    departmentSelect.add(option);
                });
            })
            .catch(error => console.error('Error fetching departments:', error));
    }

    // Function to handle form submission
    function handleFormSubmit(event) {
        event.preventDefault();

        const form = document.getElementById('staffForm');
        const formData = new FormData(form);

        const staffData = {
            firstName: formData.get('firstName'),
            lastName: formData.get('lastName'),
            email: formData.get('email'),
            contactNumber: formData.get('contactNumber'),
            departmentId: formData.get('departmentId')
        };

        formData.append('requestDto', JSON.stringify(staffData));

        fetch('http://localhost:8090/api/staff', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    alert('Staff member created successfully!');
                    form.reset();
                } else {
                    return response.json().then(error => {
                        throw new Error(error.message);
                    });
                }
            })
            .catch(error => console.error('Error creating staff member:', error));
    }

    // Attach event listener to the form
    document.getElementById('staffForm').addEventListener('submit', handleFormSubmit);

    // Populate departments dropdown on page load
    populateDepartments();
});
