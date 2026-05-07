FROM python:3.9-slim

WORKDIR /app

COPY SourceCode/requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt

COPY SourceCode/. .

EXPOSE 5000

CMD ["python", "app.py"]
