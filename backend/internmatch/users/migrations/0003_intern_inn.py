# Generated by Django 3.2.16 on 2024-10-29 08:52

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('users', '0002_auto_20241017_0841'),
    ]

    operations = [
        migrations.AddField(
            model_name='intern',
            name='inn',
            field=models.PositiveBigIntegerField(blank=True, null=True, verbose_name='ИНН'),
        ),
    ]