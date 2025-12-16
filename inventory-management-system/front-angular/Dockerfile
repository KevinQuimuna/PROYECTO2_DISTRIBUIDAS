# Etapa 1: Build
FROM node:20-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Etapa 2: Servidor Nginx
FROM nginx:alpine

# 1. IMPORTANTE: Borramos la página "Welcome to nginx" que viene por defecto
RUN rm -rf /usr/share/nginx/html/*

# 2. Copiamos los archivos compilados.
# En Angular 17+, la ruta es dist/NOMBRE_PROYECTO/browser.
# Según tus logs, tu proyecto se llama "front-angular".
COPY --from=build /app/dist/front-angular/browser /usr/share/nginx/html

# 3. Copiamos la configuración del servidor
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80