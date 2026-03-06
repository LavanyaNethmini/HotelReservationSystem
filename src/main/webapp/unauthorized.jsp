<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <meta charset="UTF-8">
    <title>403 - Access Denied</title>

    <style>
        body{
            margin:0;
            font-family: Arial, Helvetica, sans-serif;
            background:#f5f7fb;
            display:flex;
            justify-content:center;
            align-items:center;
            height:100vh;
        }

        .card{
            background:white;
            padding:40px 50px;
            border-radius:12px;
            text-align:center;
            box-shadow:0 8px 25px rgba(0,0,0,0.08);
            max-width:420px;
        }

        .icon{
            font-size:60px;
            margin-bottom:10px;
        }

        h1{
            margin:10px 0;
            color:#333;
        }

        p{
            color:#666;
            margin-bottom:30px;
        }

        .btn{
            text-decoration:none;
            background:#5b5bd6;
            color:white;
            padding:12px 24px;
            border-radius:8px;
            font-weight:500;
            transition:0.2s;
        }

        .btn:hover{
            background:#4747c6;
        }
    </style>
</head>

<body>

<div class="card">
    <div class="icon">
        <i class="fa-solid fa-circle-exclamation"></i>
    </div>

    <h1>Access Denied</h1>

    <p>
        You do not have permission to access this page.
        Please contact the system administrator if you believe this is an error.
    </p>

    <a href="dashboard.jsp" class="btn">Return to Dashboard</a>
</div>

</body>
</html>