/*
 * The MIT License (MIT) Copyright (c) 2014 karumi Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to
 * do so, subject to the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the Software. THE SOFTWARE
 * IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.karumi.rosie.demo.base.application;

import android.content.Context;
import com.karumi.rosie.daggerutils.ForApplication;
import com.karumi.rosie.domain.usercase.TaskScheduler;
import com.karumi.rosie.domain.usercase.UserCaseHandler;
import com.karumi.rosie.domain.usercase.jobqueue.TaskSchedulerJobQueue;
import com.path.android.jobqueue.JobManager;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 *
 */
@Module(library = true,
    complete = false,
    injects = { DemoApplication.class })
public class GlobalDemoAndroidModule {

  @Provides @Singleton
  public Picasso providePicasso(@ForApplication Context context) {
    return Picasso.with(context);
  }

  @Provides @Singleton
  public UserCaseHandler provideUserCaseHandler(TaskScheduler taskScheduler){
    return new UserCaseHandler(taskScheduler);
  }

  @Provides @Singleton
  public TaskScheduler provideTaskScheduler(JobManager jobManager) {
    return new TaskSchedulerJobQueue(jobManager);
  }

  @Provides @Singleton
  public JobManager provideJobManager(@ForApplication Context context) {
    return new JobManager(context);
  }
}
