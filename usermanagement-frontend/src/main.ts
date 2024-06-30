import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module'; // Adjust path as per your structure
import { environment } from './environments/environment'; // Adjust environment path as per your setup

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
